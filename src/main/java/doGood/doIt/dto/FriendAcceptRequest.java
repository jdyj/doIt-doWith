package doGood.doIt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendAcceptRequest {

    private Long memberId;

    private Long friendId;

}
