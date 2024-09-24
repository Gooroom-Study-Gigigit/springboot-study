package gooroom.gigigit.board.component.url.impl;

import gooroom.gigigit.board.component.url.UrlComponent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlComponentImpl implements UrlComponent {
    private final HttpServletRequest request;
    @Override
    public String makeProfileImgURL(String fileName, String email) {
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +
                "/users/profile/" + email;
    }
}
