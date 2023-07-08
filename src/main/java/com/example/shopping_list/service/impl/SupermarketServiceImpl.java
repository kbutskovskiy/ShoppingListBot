package com.example.shopping_list.service.impl;

import com.example.shopping_list.entity.SupermarketItem;
import com.example.shopping_list.repository.SupermarketRepository;
import com.example.shopping_list.service.SupermarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupermarketServiceImpl implements SupermarketService {

    private final SupermarketRepository supermarketRepository;

    @Override
    public void createBuyInSupermarket(Update update) {

        SupermarketItem buyToSave = new SupermarketItem();
        buyToSave.setBuy(update.getMessage().getText());
        buyToSave.setUsername(update.getMessage().getFrom().getUserName());
        buyToSave.setIsBuy(false);
        buyToSave.setTimestamp(new Timestamp(System.currentTimeMillis()));
        supermarketRepository.save(buyToSave);
    }

    @Override
    public List<SupermarketItem> getSupermarketItemListByUsername(String username) {
        return supermarketRepository.findByIsBuyFalseAndUsername(username);
    }

    @Override
    public List<SupermarketItem> getSupermarketItemList() {
        return supermarketRepository.findByIsBuyFalse();
    }
}
