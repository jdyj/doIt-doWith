package doGood.doIt.service;

import doGood.doIt.domain.*;
import doGood.doIt.dto.MissionDto;
import doGood.doIt.dto.MissionFriendAddRequest;
import doGood.doIt.dto.MissionFriendDto;
import doGood.doIt.dto.response.MissionDetailResponse;
import doGood.doIt.dto.response.MissionFriendListResponse;
import doGood.doIt.dto.response.MissionListResponse;
import doGood.doIt.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberChatRoomRepository memberChatRoomRepository;

    public MissionListResponse listAll(Long memberId) {

        List<Mission> missions = missionRepository.findAll();
        Member member = memberRepository.findById(memberId).get();
        List<MemberChatRoom> memberChatRooms = memberChatRoomRepository.findMemberChatRoomsByMember(member);
        List<ChatRoom> myChatRooms = memberChatRooms.stream()
                .map(MemberChatRoom::getChatRoom)
                .collect(Collectors.toList());

        List<Mission> myMissionList = myChatRooms.stream()
                .map(ChatRoom::getMission)
                .collect(Collectors.toList());

        boolean valid = true;
        for(Mission myMission : myMissionList) {
            for(Mission mission : missions) {
                if(mission.equals(myMission)) {
                    valid = false;
                    break;
                }
            }
            if(!valid) break;
        }
        boolean finalValid = valid;
        List<MissionDto> collect = missions.stream()
                .map(mission ->
                        new MissionDto(mission.getId(),
                                mission.getName(),
                                mission.getSponser(),
                                mission.getStartDate(),
                                mission.getEndDate(),
                                mission.getNumberOfParticipants(),
                                mission.getImage().getUrl(),
                                finalValid
                        ))
                .collect(Collectors.toList());

        return new MissionListResponse(collect.size(), collect);
    }

    public MissionDetailResponse detailAll(Long memberId ,Long missionId) {

        Optional<Mission> missionOptional = missionRepository.findById(missionId);
        Member member = memberRepository.findById(memberId).get();
        if(missionOptional.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 미션입니다");
        }

        Mission mission = missionOptional.get();
        List<Image> detailedImages = mission.getDetailedImages();

        List<String> detailedImageUrls = new ArrayList<>();

        for(Image image : detailedImages) {
            detailedImageUrls.add(image.getUrl());
        }

        MissionDetailResponse response = new MissionDetailResponse(mission.getId(),
                mission.getName(), mission.getSponser(), mission.getStartDate(), mission.getEndDate(), mission.getNumberOfParticipants(),
                detailedImageUrls,
                memberMissionRepository.findMemberMissionByMemberAndMission(member, mission).getIsCheck());

        return response;
    }

    public MissionFriendListResponse myfriendListAll(Long missionId, Long memberId) {

        Member member = memberRepository.findById(memberId).get();
        Iterator<Friend> it = member.getFriends().iterator();
        List<MissionFriendDto> collect = new ArrayList<>();
        while(it.hasNext()) {
            Friend.Key key = it.next().getKey();
            boolean canInvite = true;
            Member friend;
            if(key.getUserId().equals(memberId)) {
                friend = memberRepository.findById(key.getMemberId()).get();
                List<MemberMission> memberMissions = memberMissionRepository.findMemberMissionsByMember(friend);
                for(MemberMission memberMission : memberMissions) {
                    if (memberMission.getMission().equals(missionId)) {
                        canInvite = false;
                        break;
                    }
                }
            } else {
                friend = memberRepository.findById(key.getUserId()).get();
                List<MemberMission> memberMissions = memberMissionRepository.findMemberMissionsByMember(friend);
                for(MemberMission memberMission : memberMissions) {
                    if(memberMission.getMission().equals(missionId)) {
                        canInvite = false;
                        break;
                    }
                }
            }
            collect.add(new MissionFriendDto(friend.getName(), canInvite, friend.getProfileUrl()));
        }
        return new MissionFriendListResponse(collect.size(), collect);
    }

    public void MissionInviteFriend(MissionFriendAddRequest request) {

        Mission mission = missionRepository.findById(request.getMissionId()).get();

        ChatRoom chatRoom = ChatRoom.builder()
                .roomStatus(RoomStatus.WILLDO)
                .mission(mission)
                .build();

        ChatRoom savedRoom = chatRoomRepository.save(chatRoom);

        Member member = memberRepository.findById(request.getMemberId()).get();
        MemberChatRoom memberChatRoom = MemberChatRoom.builder()
                .chatRoom(savedRoom)
                .member(member)
                .build();

        memberChatRoomRepository.save(memberChatRoom);

        List<Member> friends = request.getFriendIds().stream()
                .map(friendId -> memberRepository.findById(friendId).get())
                .collect(Collectors.toList());

        List<MemberMission> memberMissions = friends.stream()
                .map(friend -> MemberMission.builder().mission(mission).member(friend).isCheck(true).build())
                .collect(Collectors.toList());

        memberMissionRepository.saveAll(memberMissions);

//        firebaseCloudMessageService.sendMessageTo();

    }


}
