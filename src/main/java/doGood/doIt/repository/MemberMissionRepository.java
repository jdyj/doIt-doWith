package doGood.doIt.repository;

import doGood.doIt.domain.Member;
import doGood.doIt.domain.MemberMission;
import doGood.doIt.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findMemberMissionsByMember(Member member);

    MemberMission findMemberMissionByMemberAndMission(Member member, Mission mission);
}
