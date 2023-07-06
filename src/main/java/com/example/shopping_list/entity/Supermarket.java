package com.example.shopping_list.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.glassfish.grizzly.http.util.TimeStamp;

import java.sql.Timestamp;

/**
 * Таблица с покупками в супермаркетах.
 */
@Data
@Entity
@Table(name = "supermarket")
@NoArgsConstructor
@AllArgsConstructor
public class Supermarket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String buy;

    @Column(name = "is_buy")
    private Boolean isBuy;

    @Column(name = "timestamp")
    private Timestamp timestamp;
}
