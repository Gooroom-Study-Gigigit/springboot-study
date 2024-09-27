package semo.server.domain.group_apply.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplyStatus {
    APPLIED("읽지 않음"), 
    REVIEW("읽음"),
    ACCEPTED("수락"), 
    REJECTED("거절");
    
    private final String content;
}
