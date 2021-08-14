package doGood.doIt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemberDto {

    private Long id;

    private String email;

    private String name;

    private String code;

    private String profileUrl;
}
