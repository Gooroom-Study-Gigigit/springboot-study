package semo.server.domain.interest.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Interest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestId;

    @Column(nullable = false)
    private String subject;

    */
/*@ManyToOne
    @JoinColumn(name = "parent_id")
    private Interest parentInterest;*//*
 // 자기 참조보다는 상위 관심사를
    */
/**
     * 이걸 다대다(일대다 <-> 일대다)로 풀어내야 할까?
     * 아님 enum 타입으로 관리해야 할까?
     *//*

}
*/
