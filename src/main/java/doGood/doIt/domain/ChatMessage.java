package doGood.doIt.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ChatMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String message;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public ChatMessage(ChatRoom room, Member member, String message, LocalDateTime createdAt) {
        this.room = room;
        this.member = member;
        this.message = message;
        this.createdAt = createdAt;
    }

    protected ChatMessage() {}

}
