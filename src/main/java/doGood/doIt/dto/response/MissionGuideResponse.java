package doGood.doIt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MissionGuideResponse {

    private List<String> imageUrl;

}
