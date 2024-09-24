package gooroom.gigigit.board.user.service;

import gooroom.gigigit.board.user.dto.req.UpdateUserReqDto;
import gooroom.gigigit.board.user.dto.res.UserInfoResDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    // 유저 정보를 조회합니다.
    UserInfoResDto getUserInfo(String email);

    // 유저의 프로필 이미지를 조회합니다.
    byte[] downloadProfileFromFileSystem(String email);

    // 유저의 프로필 이미지를 수정합니다.
    UserInfoResDto updateProfileImg(String email, MultipartFile multipartFile);

    // 유저의 정보를 수정합니다. (이름)
    UserInfoResDto updateUserInfo(String email, UpdateUserReqDto updateUserReqDto);

    // 유저의 정보를 삭제합니다(회원 탈퇴).
    void deleteUserInfo(String email);

}
