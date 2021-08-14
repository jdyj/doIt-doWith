package doGood.doIt.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChatRoomMemberStatusDto {

    private String name;

    private String profileUrl;

    private List<String> images;

}
