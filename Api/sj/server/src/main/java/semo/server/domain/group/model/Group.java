package semo.server.domain.group.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2500)
    private String description;

    @Column(nullable = false)
    private int maxParticipants;

    @Column(nullable = false)
    private int minAge;

    @Column(nullable = false)
    private int maxAge;

    @Column(nullable = false)
    private boolean registrationClosed;

    @Column(nullable = false)
    private String location;

}
