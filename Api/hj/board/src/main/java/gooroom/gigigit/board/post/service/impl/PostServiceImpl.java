package gooroom.gigigit.board.post.service.impl;

import gooroom.gigigit.board.component.file.FileComponent;
import gooroom.gigigit.board.exception.CustomException;
import gooroom.gigigit.board.exception.ExceptionCode;
import gooroom.gigigit.board.image.dto.res.WriteImgToFileSystemRes;
import gooroom.gigigit.board.image.entity.Image;
import gooroom.gigigit.board.image.repository.ImageRepository;
import gooroom.gigigit.board.post.dto.req.PostCreateReqDto;
import gooroom.gigigit.board.post.dto.req.PostUpdateReqDto;
import gooroom.gigigit.board.post.dto.res.PostDetailResDto;
import gooroom.gigigit.board.post.entity.Post;
import gooroom.gigigit.board.post.repository.PostRepository;
import gooroom.gigigit.board.post.service.PostService;
import gooroom.gigigit.board.user.entity.User;
import gooroom.gigigit.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FileComponent fileComponent;
    private final ImageRepository imageRepository;

    @Override
    public PostDetailResDto createPost(String email, PostCreateReqDto postCreateReqDto) {
        log.info("post의 content : {}", postCreateReqDto.content());
        User user = userRepository.findByEmail(email).orElseThrow(()-> new CustomException(ExceptionCode.USER_NOT_EXIST));
        Post post = Post.builder()
                .title(postCreateReqDto.title())
                .content(postCreateReqDto.content())
                .writer(user.getName())
                .user(user)
                .build();

        List<MultipartFile> imgFiles = postCreateReqDto.imgFiles();

        if(imgFiles !=null && !imgFiles.isEmpty()){
            for(MultipartFile img : imgFiles){
                try {
                    // 이미지 파일을 파일 시스템에 쓰는 로직
                    WriteImgToFileSystemRes writeImgToFileSystem = fileComponent.writeImgToFileSystem(
                            img.getOriginalFilename(),
                            img.getContentType(),
                            img.getInputStream().readAllBytes());
                    Image image = Image.builder()
                            .imgName(writeImgToFileSystem.imgName())
                            .imgPath(writeImgToFileSystem.imgPath())
                            .imgType(img.getContentType())
                            .post(post)
                            .build();
                    imageRepository.save(image);
                } catch (IOException e) {
                    // 예외 발생 시 처리 로직
                    e.printStackTrace();
                    // 필요에 따라 로그를 남기거나 사용자에게 에러 메시지를 전달할 수 있음
                    throw new RuntimeException("이미지 처리 중 에러가 발생했습니다.", e);
                }
            }
        }

        postRepository.save(post);
        return PostDetailResDto.from(post,true);
    }

    @Transactional(readOnly = true)
    @Override
    public PostDetailResDto getPostDetail(Long postId, String reqEmail) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException(ExceptionCode.POST_NOT_EXIST));
        String postEmail = post.getUser().getEmail();

        return PostDetailResDto.from(post, reqEmail.equals(postEmail));
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDetailResDto updatePost(Long postId, PostUpdateReqDto postUpdateReqDto) {
        Post post = postRepository.findById(postId).orElseThrow(()->new CustomException(ExceptionCode.POST_NOT_EXIST));

        if(!postUpdateReqDto.title().isEmpty()){
            post.updateTitle(postUpdateReqDto.title());
        }
        if(!postUpdateReqDto.content().isEmpty()){
            post.updateContent(postUpdateReqDto.content());
        }

        return PostDetailResDto.from(post,true);
    }
}
