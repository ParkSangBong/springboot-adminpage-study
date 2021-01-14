package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString(exclude = {"user", "item"})   // 연관관계 설정에 대한 변수에 대해선 exclude 시켜줘야 오버플로우가 발생하지 않음
@ToString(exclude = {"orderGroup", "item"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    //OrderDetail : Item = N : 1
    @ManyToOne
    private Item item;

    //OrderDetail : OrderGroup = N : 1
    @ManyToOne
    private OrderGroup orderGroup;

    // order_detail : user = N : 1, 연관관계 설정할때에는 반드시 객체이름으로.
//    @ManyToOne
//    private User user;  // user_id / hibernate에서 알아서 user_id를 찾아간다.

    // order_detail : item = N : 1
//    @ManyToOne
//    private Item item;

}
