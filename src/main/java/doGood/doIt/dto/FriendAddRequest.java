package doGood.doIt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendAddRequest {

    private Long memberId;

    private String friendEmail;

}
