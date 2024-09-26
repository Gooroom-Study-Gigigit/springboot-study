package gooroom.gigigit.board.image.repository;

import gooroom.gigigit.board.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
