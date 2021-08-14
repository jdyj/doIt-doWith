package doGood.doIt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ChatMessage {


    @EmbeddedId
    private Key key;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roomId")
    private ChatRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    private Member writer;

    private String message;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToOne
    private Image image;

    @Builder
    public ChatMessage(Key key, String message, LocalDateTime createdAt, Image image) {
        this.key = key;
        this.message = message;
        this.createdAt = createdAt;
        this.image = image;
    }

    protected ChatMessage() {}

    @Embeddable
    @AllArgsConstructor
    @Getter
    public static class Key implements Serializable {
        private Long roomId;
        private Long memberId;

        protected Key() {}
    }

}
