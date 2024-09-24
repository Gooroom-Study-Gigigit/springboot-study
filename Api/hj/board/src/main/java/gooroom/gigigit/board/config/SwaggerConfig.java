package gooroom.gigigit.board.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI(){
        // API 요청헤더에 인증 정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("JWT");

        // Security 스키마 설정
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        // OpenAPI 객체 생성 후 반환
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("JWT",bearerAuth))
                .addSecurityItem(securityRequirement)
                .info(info());
    }

    private Info info(){
        return new Info()
                .title("Toy Project Board")
                .version("0.1")
                .description("OAuth2 로그인 하이퍼 링크 입니다:<br><br>"
                        + "[Login with Naver](http://localhost:8080/oauth2/authorization/naver)&nbsp;&nbsp;&nbsp;&nbsp;"
                        + "[Login with Google](http://localhost:8080/oauth2/authorization/google)&nbsp;&nbsp;&nbsp;&nbsp;"
                        + "[Login with Kakao](http://localhost:8080/oauth2/authorization/kakao)");
    }
}
