package ru.kolesnikov.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "receipt_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReceiptDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", length = 14, scale = 1)
    private BigDecimal quantity;

    @Column(name = "price", length = 14, scale = 1)
    private BigDecimal price;

    @Column(name = "sum", length = 14, scale = 1)
    private BigDecimal sum;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "items_id")
    private Items items;

    public ReceiptDetail(BigDecimal quantity,
                         BigDecimal price,
                         Receipt receipt,
                         Items items) {
        this.quantity = quantity;
        this.price = price;
        this.sum = price.multiply(quantity);
        this.receipt = receipt;
        this.items = items;
    }


    public ReceiptDetail(Long id, BigDecimal quantity, BigDecimal price, Receipt receipt, Items items) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.sum = price.multiply(quantity);
        this.receipt = receipt;
        this.items = items;
    }
}