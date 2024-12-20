package com.example.shopping_list.repository;

import com.example.shopping_list.entity.SupermarketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс для работы с таблицей supermarket.
 * Использую jpa, запросы сами пишутся, круто)
 */
@Repository
public interface SupermarketRepository extends JpaRepository<SupermarketItem, Long> {

    /**
     * Находит все записи в таблице,
     * которые ещё не куплены, и пользователь
     * сам их создал.
     *
     * @param username юзернэйм пользователя
     * @return список записей, созданные пользователем и не купленные
     */
    List<SupermarketItem> findByIsBuyFalseAndUsername(String username);

    /**
     * Находит все записи в таблице supermarket
     * и показывает их (только не купленные)
     *
     * @return список не купленных вещей в магазине
     */
    List<SupermarketItem> findByIsBuyFalse();

    /**
     * Метод делает апдейт в базе данных,
     * признаем товар купленным.
     *
     * @param name Наименование товара
     * @return количество обновленных записей
     */
    @Modifying
    @Query("UPDATE SupermarketItem sm SET sm.isBuy = true WHERE sm.buy = :name")
    int updateBuyByName(@Param("name") String name);
}
