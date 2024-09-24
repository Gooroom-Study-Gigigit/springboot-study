package gooroom.gigigit.board.oauth2.service;

import gooroom.gigigit.board.oauth2.auth.LoginUser;
import gooroom.gigigit.board.oauth2.dto.OAuth2Response;
import gooroom.gigigit.board.oauth2.strategy.OAuth2ResponseFactory;
import gooroom.gigigit.board.user.entity.User;
import gooroom.gigigit.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final OAuth2ResponseFactory oAuth2ResponseFactory;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = oAuth2ResponseFactory.createOAuth2Response(registrationId, oAuth2User.getAttributes());
        System.out.println(oAuth2Response);

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user;

        if(userOptional.isEmpty()){
            user = User.builder()
                    .username(username)
                    .name(oAuth2Response.getName())
                    .email(oAuth2Response.getEmail())
                    .profileFilePath("/image/default.jpg")
                    .role("ROLE_USER")
                    .build();
            user = userRepository.save(user);
        }else{
            user = userOptional.get();
        }

        return new LoginUser(user, oAuth2User.getAttributes());
    }

}
