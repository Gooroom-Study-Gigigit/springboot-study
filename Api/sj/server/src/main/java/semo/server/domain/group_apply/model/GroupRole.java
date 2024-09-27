package semo.server.domain.group_apply.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GroupRole {
    USER("일반 회원"),
    LEADER("모임장");
    private final String content;
}
