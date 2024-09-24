package gooroom.gigigit.board.user.service.impl;

import gooroom.gigigit.board.component.file.FileComponent;
import gooroom.gigigit.board.exception.CustomException;
import gooroom.gigigit.board.exception.ExceptionCode;
import gooroom.gigigit.board.user.dto.req.UpdateUserReqDto;
import gooroom.gigigit.board.user.dto.res.UserInfoResDto;
import gooroom.gigigit.board.user.entity.User;
import gooroom.gigigit.board.user.repository.UserRepository;
import gooroom.gigigit.board.user.service.UserService;
import gooroom.gigigit.board.util.FileNameGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FileComponent fileComponent;

    @Value("${fileSystemPath}")
    private String FOLDER_PATH;

    @Override
    @Transactional(readOnly = true)
    public UserInfoResDto getUserInfo(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException(ExceptionCode.USER_NOT_EXIST));
        return UserInfoResDto.from(user);
    }

    @Override
    public byte[] downloadProfileFromFileSystem(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException(ExceptionCode.USER_NOT_EXIST));
        byte[] profileImg= fileComponent.downloadProfileFromFileSystem(user.getProfileFilePath());
        return profileImg;
    }


    @Override
    public UserInfoResDto updateProfileImg(String email, MultipartFile updateFile) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException(ExceptionCode.USER_NOT_EXIST));
        String updateFileName = FileNameGenerator.generatorName(updateFile.getOriginalFilename());
        String updateFilePath = FOLDER_PATH + updateFileName;
        log.info("새로운 이미지 경로 : {}", updateFilePath);

        fileComponent.updateFileSystemProfileImg(user.getProfileFilePath(), updateFilePath,updateFile);

        user.updateUserProfileUrl(updateFilePath);

        return UserInfoResDto.from(user);
    }

    @Override
    public UserInfoResDto updateUserInfo(String email, UpdateUserReqDto updateUserReqDto) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException(ExceptionCode.USER_NOT_EXIST));
        if(updateUserReqDto.name() != null){
            user.updateUserName(updateUserReqDto.name());
        }
        return UserInfoResDto.from(user);
    }

    @Override
    public void deleteUserInfo(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException(ExceptionCode.USER_NOT_EXIST));
        userRepository.deleteById(user.getId());
    }
}
