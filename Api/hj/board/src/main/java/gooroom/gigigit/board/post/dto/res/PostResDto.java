package gooroom.gigigit.board.post.dto.res;

import lombok.Builder;

@Builder
public record PostResDto(
        Long postId,
        String title,
        String writer
){}
