package doGood.doIt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MissionFriendAddRequest {

    private Long memberId;

    private Long missionId;

    private List<Long> friendIds;
}
