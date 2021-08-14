package doGood.doIt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MissionFriendListResponse<T> {

    private int count;

    private T data;
}
