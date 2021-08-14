package doGood.doIt.dto;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatRoomCreateRequest {

    private Long memberId;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;



}
