package gooroom.gigigit.board.user.dto.res;

import gooroom.gigigit.board.user.entity.User;
import lombok.Builder;

@Builder
public record UserInfoResDto(
        Long id,
        String username,
        String name,
        String email,
        String profileImgUrl
){
    public static UserInfoResDto from(User user){
        return UserInfoResDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .profileImgUrl(user.getProfileFilePath())
                .build();
    }
}
