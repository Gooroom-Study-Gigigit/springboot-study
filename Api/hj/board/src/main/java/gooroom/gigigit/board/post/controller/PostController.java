package gooroom.gigigit.board.post.controller;

import gooroom.gigigit.board.common.CommonResponseDto;
import gooroom.gigigit.board.post.dto.req.PostCreateReqDto;
import gooroom.gigigit.board.post.dto.req.PostUpdateReqDto;
import gooroom.gigigit.board.post.dto.res.PostDetailResDto;
import gooroom.gigigit.board.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;

@Tag(name = "[Post] Post API")
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {
    private final PostService postService;

    @Operation(summary = "게시물 상세 정보 조회", description = "postId에 해당하는 게시물을 조회")
    @GetMapping("/{postId}")
    public ResponseEntity<CommonResponseDto<PostDetailResDto>> getPostDetail(@PathVariable("postId") Long postId,
                                                                            @AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails.getUsername();
        PostDetailResDto postDetailResDto = postService.getPostDetail(postId, email);

        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponseDto<>(
                "게시물 상세 정보를 성공적으로 조회하였습니다.",
                postDetailResDto));
    }

    @Operation(summary = "게시물 생성", description = "토큰 정보 기반 유저가 게시물을 생성")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommonResponseDto<PostDetailResDto>> createPost(
            final @ModelAttribute("postCreateReqDto") PostCreateReqDto postCreateReqDto,
            final @AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails.getUsername();
        log.info("email : {}", email);
        PostDetailResDto postDetailResDto = postService.createPost(email,postCreateReqDto);

        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponseDto<>(
                "게시물을 성공적으로 생성하였습니다.",
                postDetailResDto));
    }

    @Operation(summary = "게시물 수정", description = "postId에 해당하는 게시물을 수정합니다")
    @PatchMapping("/{postId}")
    public ResponseEntity<CommonResponseDto<PostDetailResDto>> updatePost(@PathVariable("postId") Long postId,
                                                                          @RequestBody PostUpdateReqDto postUpdateReqDto){
        PostDetailResDto postDetailResDto = postService.updatePost(postId, postUpdateReqDto);

        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponseDto<>(
                "게시물을 성공적으로 수정하였습니다.",
                postDetailResDto));
    }
    
    @Operation(summary = "게시물 삭제", description = "postId에 해당하는 게시물을 삭제합니다")
    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponseDto<Void>> deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponseDto<>(
                "게시물을 성공적으로 삭제하였습니다",
                null));
    }
    
}
