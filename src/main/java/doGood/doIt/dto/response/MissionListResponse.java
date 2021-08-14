package doGood.doIt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MissionListResponse<T> {

    private int count;

    private T data;

}
