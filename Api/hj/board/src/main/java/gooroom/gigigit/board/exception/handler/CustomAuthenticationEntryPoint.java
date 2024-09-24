package gooroom.gigigit.board.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import gooroom.gigigit.board.common.CommonResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        CommonResponseDto commonResponseDto = new CommonResponseDto("인증되지 않은 사용자입니다. 로그인을 수행하세요.",null);
        try{
            response.getWriter().write(objectMapper.writeValueAsString(commonResponseDto));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
