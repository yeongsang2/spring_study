package com.jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") // 어느 column과 매핑
    private Member member;


    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id") // 연관관계의 주인  order 테이블에서 delivery_id foreign key 관리
    private Delivery delivery;

    private LocalDateTime orderDate; //hibernate 알아서 지원

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //[ORDER, CANCEL]



}
