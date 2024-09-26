package gooroom.gigigit.board.post.repository;

import gooroom.gigigit.board.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
