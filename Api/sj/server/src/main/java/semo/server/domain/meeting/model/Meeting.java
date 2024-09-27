package semo.server.domain.meeting.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import semo.server.domain.group.model.Group;
import semo.server.domain.image.model.Image;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int maxParticipants;

    @Column(nullable = false)
    private String expectedCost; // 단순 문자열로 처리할지, 정수형으로 금액을 잡아서 처리할지

    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
}
