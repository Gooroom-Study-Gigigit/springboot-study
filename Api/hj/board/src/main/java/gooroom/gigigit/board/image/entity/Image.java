package gooroom.gigigit.board.image.entity;

import gooroom.gigigit.board.post.entity.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Table(name ="images")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long imgId;

    @Column(name = "img_name", nullable = false)
    private String imgName;

    @Column(name = "img_path", nullable = false)
    private String imgPath;

    @Column(name = "img_type", nullable = false)
    private String imgType;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id")
    private Post post;


    @Builder
    protected Image(Long imgId, String imgName, String imgPath, String imgType,Post post){
        this.imgId = imgId;
        this.imgName = imgName;
        this.imgPath = imgPath;
        this.imgType = imgType;
        this.post = post;
    }
}
