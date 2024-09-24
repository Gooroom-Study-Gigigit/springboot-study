package gooroom.gigigit.board.user.entity;

import gooroom.gigigit.board.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String role;

    @Column(name="profile_file_path")
    private String profileFilePath;

    @Builder
    private User(Long id, String username, String name, String email, String role,String profileFilePath)
    {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
        this.profileFilePath = profileFilePath;
    }

    public void updateUserName(String name){
        this.name = name;
    }
    public void updateUserProfileUrl(String profileFilePath){
        this.profileFilePath =profileFilePath;
    }
}
