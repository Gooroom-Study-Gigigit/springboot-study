package gooroom.gigigit.board.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import gooroom.gigigit.board.common.CommonResponseDto;
import gooroom.gigigit.board.jwt.JwtProvider;
import gooroom.gigigit.board.user.entity.User;
import gooroom.gigigit.board.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 인증 성공 시 처리하는 기본 핸들러
// 인증 성공 후 사용자를 특정 URL로 리다이렉트, 추가적인 처리 하기
@Log4j2
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // LoginUser 객체를 반환하면, 해당 객체가 스프링 시큐리티의 Authentication 객체에 저장
        System.out.println(userDetails.getUsername());
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 유저입니다."));
        log.info("유저 정보 username : "+ user.getId() +", email  : " +user.getEmail());
        String accessToken = jwtProvider.createJwt(user.getEmail(),user.getUsername(), user.getRole(), user.getName(),user.getId());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        CommonResponseDto commonResponseDto = new CommonResponseDto("Access token 발급 성공",accessToken);

        // Json 직렬화
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(commonResponseDto));
        response.getWriter().flush();
    }
}
