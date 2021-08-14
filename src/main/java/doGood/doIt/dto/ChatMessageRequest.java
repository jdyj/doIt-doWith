package doGood.doIt.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageRequest {

    private Long writerId;

    private String message;

    private LocalDateTime createdAt;

}
