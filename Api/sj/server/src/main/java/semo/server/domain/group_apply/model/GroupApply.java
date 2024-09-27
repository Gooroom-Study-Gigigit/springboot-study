package semo.server.domain.group_apply.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import semo.server.domain.group.model.Group;
import semo.server.domain.user.model.User;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupApply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplyStatus applyStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupRole groupRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

}
