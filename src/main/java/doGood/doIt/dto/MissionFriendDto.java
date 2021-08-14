package doGood.doIt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MissionFriendDto {

    private String name;

    private Boolean canInvite;

    private String profileImg;

}
