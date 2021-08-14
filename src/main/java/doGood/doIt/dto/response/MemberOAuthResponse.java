package doGood.doIt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MemberOAuthResponse {

    private Long id;

    private String name;

    private String email;

    private String code;

    private String profileUrl;

}
