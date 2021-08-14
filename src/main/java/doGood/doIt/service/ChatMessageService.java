package doGood.doIt.service;

import doGood.doIt.domain.ChatMessage;
import doGood.doIt.domain.ChatRoom;
import doGood.doIt.domain.Member;
import doGood.doIt.dto.ChatMessageRequest;
import doGood.doIt.repository.ChatMessageRepository;
import doGood.doIt.repository.ChatRoomRepository;
import doGood.doIt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    public void save(ChatMessageRequest request, String roomId) {

        ChatRoom room = chatRoomRepository.findById(Long.parseLong(roomId)).get();
        Member member = memberRepository.findById(Long.parseLong(request.getWriterId())).get();

        ChatMessage chatMessage = ChatMessage.builder()
                .room(room)
                .member(member)
                .message(request.getMessage())
                .createdAt(LocalDateTime.now())
                .build();
        chatMessageRepository.save(chatMessage);
    }
}
