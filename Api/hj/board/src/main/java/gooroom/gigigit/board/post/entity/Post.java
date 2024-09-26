package gooroom.gigigit.board.post.entity;

import gooroom.gigigit.board.common.BaseTimeEntity;
import gooroom.gigigit.board.image.entity.Image;
import gooroom.gigigit.board.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @Builder
    protected Post(String title, String content, String writer, User user, List<Image> images){
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.user = user;
        this.images = images;
    }

    public void updateTitle(String title){this.title = title;}
    public void updateContent(String content){this.content = content;}
    public void updateWriter(String writer){this.writer = writer;}
}
