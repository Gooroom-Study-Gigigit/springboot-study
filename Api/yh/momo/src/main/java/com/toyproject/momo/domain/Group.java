package com.toyproject.momo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private GroupCategory category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "group_name",nullable = false)
    private String groupName;

    @Column
    private String description;

    @Column
    private String profileImage;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}