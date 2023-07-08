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

    /**
     * Метод возвращает записи из таблицы
     * по имени пользователя.
     *
     * @param username имя пользователя
     * @return список товаров, которые нужно купить
     */
    List<SupermarketItem> getSupermarketItemListByUsername(String username);

    /**
     * Метод возвращает весь список покупок
     * из таблицы
     *
     * @return список покупок
     */
    List<SupermarketItem> getSupermarketItemList();

    /**
     * Метод обновляет запись и мы считаем, что купили продукт
     * @param itemName название продукта
     * @return количество обновленных записей
     */
    int updateSupermarketTable(String itemName);
}
