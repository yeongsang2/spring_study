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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // 어느 column과 매핑
    private Member member;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL) //orderItems 에 데이터를 넣어두고 order을 저장하면 orderitems도 자동 저장
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  //order저장시 delivery도 persist 됨
    @JoinColumn(name = "delivery_id") // 연관관계의 주인  order 테이블에서 delivery_id foreign key 관리
    private Delivery delivery;

    private LocalDateTime orderDate; //hibernate 알아서 지원

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //[ORDER, CANCEL]


    //연관관계 편의 메소드  --> 양방향시, 핵심적으로 로직 control? 하는 쪽이 들고 있기
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItems(OrderItem orderItem){ // 비즈니스 로직상?>
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
    // 연관관계 편의 메소드


    //== 생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItems(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;

    }

    //== 비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems){ // 각각의 orderItem cancel
            orderItem.cancel();
        }

    }

    //== 조회 로직 ==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
//        int totalPrice = 0;
//        for(OrderItem orderItem : orderItems){
//            totalPrice += orderItem.getTotalPrice();
//        }
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice).sum();

    }

}
