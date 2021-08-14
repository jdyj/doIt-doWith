package doGood.doIt.controller;

import doGood.doIt.dto.ChatMessageRequest;
import doGood.doIt.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/{roomId}")
    @SendTo("/topic/{roomId}")
    public String enter(ChatMessageRequest message, @PathVariable Long roomId) {
        chatMessageService.save(message, roomId);

        log.info("룰루랄라 메시지 잘안된당");

        return message.getMessage();
    }

}
