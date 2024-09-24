package gooroom.gigigit.board.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import gooroom.gigigit.board.common.CommonResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding("UTF-8");
        CommonResponseDto commonResponseDto = new CommonResponseDto("해당 리소스에 접근할 권한이 없습니다",null);

        try {
            response.getWriter().write(objectMapper.writeValueAsString(commonResponseDto));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
