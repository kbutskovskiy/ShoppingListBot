package com.example.shopping_list.repository;

import com.example.shopping_list.entity.SupermarketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupermarketRepository extends JpaRepository<SupermarketItem, Long> {

    List<SupermarketItem> findByIsBuyFalseAndUsername(String username);

    List<SupermarketItem> findByIsBuyFalse();
}
