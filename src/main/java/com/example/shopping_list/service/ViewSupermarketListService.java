package com.example.shopping_list.service;

import com.example.shopping_list.entity.SupermarketItem;

import java.util.List;

public interface ViewSupermarketListService {

    List<SupermarketItem> getSupermarketItemListByUsername(String username);
    List<SupermarketItem> getSupermarketItemList();
}
