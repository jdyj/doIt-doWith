package doGood.doIt.dto;

import doGood.doIt.domain.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ChatRoomDto {

    private Long id;
    private String name;
    private String sponser;
    private LocalDateTime startDate;
    private List<String> members;
    private RoomStatus roomStatus;

}
