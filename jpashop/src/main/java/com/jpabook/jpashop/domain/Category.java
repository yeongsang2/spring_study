package com.jpabook.jpashop.domain;


import com.jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;


    @ManyToMany
    @JoinTable(name = "category_item",
    joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")
    ) //db상에서는 중간 table필요 ->추후에 변경하자
    private List<Item> items = new ArrayList<>();

    // self 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY) // 내부모니까 many TO One
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // 양방향 편의 메소드
    public void addChildCategory(Category child){

        this.child.add(child); // 자식 추가
        child.setParent(this); // 부모 설정

    }
}
