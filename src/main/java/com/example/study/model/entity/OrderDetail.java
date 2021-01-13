package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString(exclude = {"user", "item"})   // 연관관계 설정에 대한 변수에 대해선 exclude 시켜줘야 오버플로우가 발생하지 않음
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private Long orderGroupId;

    private Long itemId;

    // order_detail : user = N : 1, 연관관계 설정할때에는 반드시 객체이름으로.
//    @ManyToOne
//    private User user;  // user_id / hibernate에서 알아서 user_id를 찾아간다.

    // order_detail : item = N : 1
//    @ManyToOne
//    private Item item;

}
