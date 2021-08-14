package doGood.doIt.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class FriendListResponse<T> {

    private int count;

    private T data;

}
