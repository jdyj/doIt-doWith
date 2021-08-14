package doGood.doIt.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import doGood.doIt.domain.Member;
import doGood.doIt.dto.response.MemberOAuthResponse;
import doGood.doIt.repository.MemberRepository;
import doGood.doIt.util.RandomString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberOAuthResponse kakaoApiSignUp(String accessToken) {

        String requestURL = "https://kapi.kakao.com/v2/user/me";

        try {

            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            String result = "";

            while((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            JsonObject profile = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject();

            String nickname = profile.getAsJsonObject().get("nickname").getAsString();
            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

            String imageUrl = profile.getAsJsonObject().get("thumbnail_image_url").getAsString();
            Optional<Member> memberOptional = memberRepository.findByEmail(email);
            MemberOAuthResponse response;

            if(memberOptional.isEmpty()) {
                Member member = Member.builder()
                        .name(nickname)
                        .code(new RandomString(8).nextString())
                        .email(email)
                        .createdAt(LocalDateTime.now())
                        .profileUrl(imageUrl)
                        .build();

                Member savedMember = memberRepository.save(member);
                return new MemberOAuthResponse(savedMember.getId(), savedMember.getName(), savedMember.getEmail(), savedMember.getCode(), savedMember.getProfileUrl());
            }
            Member findMember = memberOptional.get();

            return new MemberOAuthResponse(findMember.getId(), findMember.getName(), findMember.getEmail(), findMember.getCode(), findMember.getProfileUrl());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
