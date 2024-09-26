package gooroom.gigigit.board.post.dto.req;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record PostCreateReqDto(
        String title,
        String content,
        List<MultipartFile> imgFiles
){}
