package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderDetailList", "partner"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private Integer price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;
    //Item : Partner = N : 1
    @ManyToOne
    private Partner partner;

    //Item : OrderDetail = 1 : n
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

    //  fetchtype LAZY = 지연로딩 , EAGER = 즉시로딩
    //  LAZY 로딩은 따로 메서드를 호출하지 않는 이상(변수에 대해서 GET 메써드를 호출하지 않는 이상
    //  연관관계가 설정된 테이블에 대해서 SELECT를 하지 않겠다는 것이 LAZY 옵션
    //  EAGER 로딩은 즉시 모든것을 다 로딩을 하겠다. 연관관계가 설정된 모든 테이블에 대해서 조인이
    //  일어나는것이 EAGER 옵션, 이 때 데이터가 많은 테이블에 대해서 EAGER 옵션이 설정되어있다면
    //  해당 쿼리문이 실행이 될 때(한가지 아이템만 SELECT 하더라도) 모든 데이터를 다 조인걸어서
    //  가져오기때문에 성능의 저하를 유발한다거나, 가지고 오지 못할 수 있는 위험성이 생긴다.
    //  그렇기때문에 반드시 연관관계 설정에 대해서는 LAZY 옵션을 추천.
    //  EAGER 옵션은 1:1, 한 건만 존재할 때에(N:1) 보통추천. EAGER는 1:1이 디폴트.
    //  LAZY = SELECT * FROM item where id = ?
    //  EAGER =
    //  item_id = order_detail.item_id
    //  user_id = order_detail.user_id
    //  where item.id = ?
    //  JOIN item item0_ left outer join order_detail orderdetai1_ on item0_.id=orderdetai1_.item_id left outer join user user2_ on orderdetai1_.user_id=user2_.id
    // item : order_detail = 1 : N
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
//    private List<OrderDetail> orderDetailList;

}
