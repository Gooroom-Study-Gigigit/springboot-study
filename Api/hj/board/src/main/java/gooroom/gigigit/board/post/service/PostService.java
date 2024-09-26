package gooroom.gigigit.board.post.service;

import gooroom.gigigit.board.post.dto.req.PostCreateReqDto;
import gooroom.gigigit.board.post.dto.req.PostUpdateReqDto;
import gooroom.gigigit.board.post.dto.res.PostDetailResDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    // 게시물을 생성
    PostDetailResDto createPost(String email, PostCreateReqDto postCreateResDto);

    // 게시물 클릭 -> 게시물 디테일 보기
    PostDetailResDto getPostDetail(Long postId, String email);

    // 게시물 삭제
    void deletePost(Long postId);

    // 게시물 수정
    PostDetailResDto updatePost(Long postId, PostUpdateReqDto postUpdateReqDto);

}
