package doGood.doIt.service;

import doGood.doIt.domain.ChatRoom;
import doGood.doIt.domain.Image;
import doGood.doIt.domain.Member;
import doGood.doIt.repository.ChatRoomRepository;
import doGood.doIt.repository.ImageRepository;
import doGood.doIt.repository.MemberChatRoomRepository;
import doGood.doIt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final MemberChatRoomRepository memberChatRoomRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;

    public void upload(String memberId, String roomId) {

        Member member = memberRepository.findById(Long.parseLong(memberId)).get();
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(Long.parseLong(roomId));



    }
}
