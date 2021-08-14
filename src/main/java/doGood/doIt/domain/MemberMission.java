package doGood.doIt.domain;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private Boolean isActive;

    @Builder
    public MemberMission(Member member, Mission mission, Boolean isActive) {
        this.member = member;
        this.mission = mission;
        this.isActive = isActive;
    }

    protected MemberMission() {
    }
}
