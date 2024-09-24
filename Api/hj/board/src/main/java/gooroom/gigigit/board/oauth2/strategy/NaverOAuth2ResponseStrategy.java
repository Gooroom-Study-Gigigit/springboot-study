package gooroom.gigigit.board.oauth2.strategy;

import gooroom.gigigit.board.oauth2.dto.NaverResponse;
import gooroom.gigigit.board.oauth2.dto.OAuth2Response;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NaverOAuth2ResponseStrategy implements OAuth2ResponseStrategy{
    @Override
    public String getProviderName() {
        return "naver";
    }

    @Override
    public OAuth2Response createOAuth2Response(Map<String, Object> attributes) {
        return new NaverResponse(attributes);
    }
}
