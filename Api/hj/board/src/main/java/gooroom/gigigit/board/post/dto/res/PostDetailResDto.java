package gooroom.gigigit.board.post.dto.res;

import gooroom.gigigit.board.post.entity.Post;
import lombok.Builder;

@Builder
public record PostDetailResDto(
        Long postId,
        String title,
        String content,
        String writer,
        boolean isOwnPost
){
    public static PostDetailResDto from(Post post,boolean isOwnPost){
        return PostDetailResDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .isOwnPost(isOwnPost)
                .build();
    }
}
