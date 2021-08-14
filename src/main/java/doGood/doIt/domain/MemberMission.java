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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private Boolean isCheck;

    @Builder
    public MemberMission(Member member, Mission mission, Boolean isCheck) {
        this.member = member;
        this.mission = mission;
        this.isCheck = isCheck;
    }

    protected MemberMission() {
    }
}
