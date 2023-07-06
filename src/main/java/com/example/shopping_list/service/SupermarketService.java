package com.example.shopping_list.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface SupermarketService {

    void createBuyInSupermarket(Update update);
}
