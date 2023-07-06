package com.example.shopping_list.service.impl;

import com.example.shopping_list.entity.Supermarket;
import com.example.shopping_list.repository.SupermarketRepository;
import com.example.shopping_list.service.SupermarketService;
import lombok.RequiredArgsConstructor;
import org.glassfish.grizzly.http.util.TimeStamp;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class SupermarketServiceImpl implements SupermarketService {

    private final SupermarketRepository supermarketRepository;

    @Override
    public void createBuyInSupermarket(Update update) {

        Supermarket buyToSave = new Supermarket();
        buyToSave.setBuy(update.getMessage().getText());
        buyToSave.setUsername(update.getMessage().getFrom().getUserName());
        buyToSave.setIsBuy(false);
        buyToSave.setTimestamp(new Timestamp(System.currentTimeMillis()));
        supermarketRepository.save(buyToSave);
    }
}
