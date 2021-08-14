package doGood.doIt.service;

import doGood.doIt.domain.ChatMessage;
import doGood.doIt.dto.ChatMessageRequest;
import doGood.doIt.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public void save(ChatMessageRequest request, Long roomId) {
        ChatMessage.Key key = new ChatMessage.Key(roomId, request.getWriterId());
        ChatMessage chatMessage = ChatMessage.builder()
                .key(key)
                .message(request.getMessage())
                .createdAt(request.getCreatedAt())
                .build();
        chatMessageRepository.save(chatMessage).getKey();
    }
}
