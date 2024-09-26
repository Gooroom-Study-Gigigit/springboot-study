package com.toyproject.momo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ChatRoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 고유 ID

    @ManyToOne // 하나의 채팅방에 속함
    @JoinColumn(name = "chatroom_id", nullable = false)
    private ChatRoom chatRoom;  // 채팅방 ID (외래 키, ChatRoom과 연결)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 사용자 ID (외래 키, User와 연결)

    @ManyToOne
    @JoinColumn(name = "last_read_message_id")  // 외래 키 설정
    private ChatMessage chatMessage;

    @Column(name="joined_at",nullable = false)
    private LocalDateTime joinedAt;  // 채팅방에 참여한 날짜
}