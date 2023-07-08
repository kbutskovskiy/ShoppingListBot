package com.example.shopping_list.service.impl;

import com.example.shopping_list.entity.SupermarketItem;
import com.example.shopping_list.repository.SupermarketRepository;
import com.example.shopping_list.service.ViewSupermarketListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewSupermarketListServiceImpl implements ViewSupermarketListService {

    private final SupermarketRepository supermarketRepository;

    @Override
    public List<SupermarketItem> getSupermarketItemListByUsername(String username) {
        return supermarketRepository.findByIsBuyFalseAndUsername(username);
    }

    @Override
    public List<SupermarketItem> getSupermarketItemList() {
        return supermarketRepository.findByIsBuyFalse();
    }
}
