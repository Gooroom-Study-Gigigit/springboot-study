package gooroom.gigigit.board.oauth2.strategy;

import gooroom.gigigit.board.oauth2.dto.GoogleResponse;
import gooroom.gigigit.board.oauth2.dto.OAuth2Response;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GoogleOAuth2ResponseStrategy implements OAuth2ResponseStrategy{
    @Override
    public String getProviderName() {
        return "google";
    }

    @Override
    public OAuth2Response createOAuth2Response(Map<String, Object> attributes) {
        System.out.println(attributes);
        return new GoogleResponse(attributes);
    }
}
