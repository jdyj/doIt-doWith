package doGood.doIt.service;
import doGood.doIt.domain.Friend;
import doGood.doIt.domain.Member;
import doGood.doIt.dto.FriendAcceptRequest;
import doGood.doIt.dto.FriendAddRequest;
import doGood.doIt.dto.MemberDto;
import doGood.doIt.dto.response.FriendListResponse;
import doGood.doIt.repository.FriendRepository;
import doGood.doIt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final EntityManager em;
    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    public Friend.Key addFriend(FriendAddRequest request) {

        Member member = memberRepository.findById(request.getMemberId()).get();
        Member friendMember = validateEmail(request.getFriendEmail());

        duplicateFriend(member.getId(), friendMember.getId());
        Friend friend = Friend.builder()
                .user(member)
                .member(friendMember)
                .isActive(false)
                .build();

        return friendRepository.save(friend).getKey();

    }

    @Transactional
    public Friend.Key acceptFriend(FriendAcceptRequest request) {
        Friend friend = em.find(Friend.class, new Friend.Key(request.getMemberId(), request.getFriendId()));
        friend.setIsActive(true);
        return friend.getKey();
    }

    @Transactional
    public FriendListResponse friendList(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if(memberOptional.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }

        Iterator<Friend> it = memberOptional.get().getFriends().iterator();
        List<Member> memberList = new ArrayList<>();
        while(it.hasNext()) {
            Friend.Key key = it.next().getKey();

            if(key.getUserId().equals(memberId)) {
                memberList.add(memberRepository.findById(key.getMemberId()).get());
            } else {
                memberList.add(memberRepository.findById(key.getUserId()).get());
            }
        }

        List<MemberDto> collect = memberList.stream()
                .map(member -> new MemberDto(member.getId(),member.getEmail(),member.getName(),member.getCode(),member.getProfileUrl()))
                .collect(Collectors.toList());

        return new FriendListResponse(collect.size(), collect);
    }

    private void duplicateFriend(Long userId, Long memberId) {

        Friend.Key key1 = new Friend.Key(userId, memberId);
        Friend.Key key2 = new Friend.Key(memberId, userId);
        Optional<Friend> friend1Optional = friendRepository.findById(key1);
        Optional<Friend> friend2Optional = friendRepository.findById(key2);

        if(friend1Optional.isPresent() || friend2Optional.isPresent()) {
            throw new IllegalStateException("존재하는 친구입니다");
        }
    }

    private Member validateEmail(String email) {

        Optional<Member> findMember = memberRepository.findByEmail(email);

        if(findMember.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 아이디 입니다.");
        }

        return findMember.get();

    }


}