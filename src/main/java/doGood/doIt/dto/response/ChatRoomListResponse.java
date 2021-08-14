package doGood.doIt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Data
public class ChatRoomListResponse<T> {

    private int count;

    private T data;

}
