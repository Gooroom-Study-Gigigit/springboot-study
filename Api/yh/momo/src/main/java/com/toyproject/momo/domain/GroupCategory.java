package com.toyproject.momo.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GroupCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // 예: 운동, 영화, 요리 등
}