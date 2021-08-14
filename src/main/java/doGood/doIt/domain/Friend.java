package doGood.doIt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;

import static java.awt.RenderingHints.*;

@Entity
@Getter
public class Friend {

    @EmbeddedId
    private Key key = new Key();

    @ManyToOne
    @MapsId("userId")
    private Member user;

    @ManyToOne
    @MapsId("memberId")
    private Member member;

    private Boolean isActive;

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
        user.getFriends().add(this);
        member.getFriends().add(this);
    }

    @Builder
    public Friend(Member user, Member member, Boolean isActive) {
        this.user = user;
        this.member = member;
        this.isActive = isActive;
    }

    protected Friend() {}

    @Embeddable
    @AllArgsConstructor
    @Getter
    public static class Key implements Serializable {
        private Long userId;
        private Long memberId;

        protected Key() {}
    }
}
