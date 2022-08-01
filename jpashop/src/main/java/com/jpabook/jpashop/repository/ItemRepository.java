package com.jpabook.jpashop.repository;


import com.jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor //final keyword 자동 autowired (주입) 생성자 만들어줌
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){

        if(item.getId() == null){
            em.persist(item);
        } else{
            em.merge(item); // update 비슷
        }

    }


    public Item findOne(Long id){
        return em.find(Item.class, id);
    }
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
