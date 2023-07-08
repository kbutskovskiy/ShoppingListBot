package com.example.shopping_list.service;

import com.example.shopping_list.entity.SupermarketItem;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

/**
 * Сервис для обработки запросов, связанных с таблицей
 * супермаркет
 */
public interface SupermarketService {

    /**
     * Метод создает покупку в таблице супермаркет
     *
     * @param update -- передаем update из основного класса.
     */
    void createBuyInSupermarket(Update update);

    List<SupermarketItem> getSupermarketItemListByUsername(String username);
    List<SupermarketItem> getSupermarketItemList();
}
