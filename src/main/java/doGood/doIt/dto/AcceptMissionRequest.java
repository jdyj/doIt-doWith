package doGood.doIt.dto;

import lombok.Getter;

@Getter
public class AcceptMissionRequest {

    private Long memberId;

    private Long missionId;

    private Long friendId;
}
