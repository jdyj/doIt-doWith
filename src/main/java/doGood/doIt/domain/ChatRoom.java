package doGood.doIt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.persistence.*;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Mission mission;

    @Builder
    public ChatRoom(RoomStatus roomStatus, Mission mission) {
        this.roomStatus = roomStatus;
        this.mission = mission;
    }

    protected ChatRoom() {}
}
