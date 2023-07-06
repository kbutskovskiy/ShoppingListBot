package com.example.shopping_list.repository;

import com.example.shopping_list.entity.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.Update;

@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {
}
