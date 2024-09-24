package gooroom.gigigit.board.oauth2.strategy;

import gooroom.gigigit.board.oauth2.dto.KakaoResponse;
import gooroom.gigigit.board.oauth2.dto.OAuth2Response;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KakaoOAuth2ResponseStrategy implements OAuth2ResponseStrategy{
    @Override
    public String getProviderName() {
        return "kakao";
    }

    @Override
    public OAuth2Response createOAuth2Response(Map<String, Object> attributes) {
        System.out.println(attributes);
        return new KakaoResponse(attributes);
    }
}
