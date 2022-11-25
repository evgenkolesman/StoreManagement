package ru.kolesnikov.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_table")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "barcode", length = 13, nullable = false, unique = true)
    private String barcode;

    @Column(name = "price", nullable = false, scale = 1)
    private BigDecimal price;

    @Setter
    @Column(name = "balance")
    private int balance;

    public Items(String itemName, String barcode, BigDecimal price) {
        this.itemName = itemName;
        this.barcode = barcode;
        this.price = price;
    }
}
