package com.jpabook.jpashop.service;


import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional //transaction commit -> flush
    public void updateItem(Long itemId, UpdateItemDto itemDto){

        Item findItem = itemRepository.findOne(itemId);

        findItem.setPrice(itemDto.getPrice());
        findItem.setName(itemDto.getName());
        findItem.setStockQuantity(itemDto.getStockQuantity());
        //findItem에 update method 만들기?
        // find.change(name, price, stockQuantity)
    }

    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

}
