package doGood.doIt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class MissionDetailResponse {

    private Long id;

    private String name;

    private String sponser;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String numberOfParticipants;

    private List<String> detailedImageUrls;

    private Boolean isCheck;

}
