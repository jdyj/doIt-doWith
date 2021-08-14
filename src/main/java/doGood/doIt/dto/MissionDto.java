package doGood.doIt.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class MissionDto {

    private Long id;

    private String name;

    private String sponser;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String numberOfParticipants;

    private String imageUrl;

    private Boolean isActive;

    private String reward;

    private String missionContent;

}
