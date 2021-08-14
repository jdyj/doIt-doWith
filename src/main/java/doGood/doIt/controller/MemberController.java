package doGood.doIt.controller;

import doGood.doIt.dto.response.MemberOAuthResponse;
import doGood.doIt.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(tags = "회원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "카카오 로그인")
    @GetMapping("/oauthKakao")
    public ResponseEntity<MemberOAuthResponse> kakaoLogin(HttpServletRequest request, HttpServletResponse response) {
        String access_token = request.getHeader("Authorization");
        if(access_token == null) {
            throw new IllegalStateException("유효하지 않는 요청입니다");
        }
        MemberOAuthResponse res = memberService.kakaoApiSignUp(access_token);
        Cookie cookie = new Cookie("memberId", String.valueOf(res.getId()));
        response.addCookie(cookie);
        return ResponseEntity.ok()
                .body(res);
    }

}
