package doGood.doIt.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sponser;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String reward;

    private String numberOfParticipants;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "mission")
    private List<Image> detailedImages = new ArrayList<>();

}
