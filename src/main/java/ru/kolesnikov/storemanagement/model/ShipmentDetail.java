package ru.kolesnikov.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "shipment_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShipmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", length = 14, scale = 1)
    private BigDecimal quantity;

    @Column(name = "price", length = 14, scale = 1)
    private BigDecimal price;

    @Formula("quantity * price")
    private BigDecimal sum;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "items_id")
    private Items items;

    public ShipmentDetail(Shipment shipment,
                          BigDecimal quantity,
                          BigDecimal price,
                          Items items) {
        this.quantity = quantity;
        this.price = price;
        this.shipment = shipment;
        this.items = items;
    }


    public ShipmentDetail(Long id,
                          Shipment shipment,
                          BigDecimal quantity,
                          BigDecimal price,
                          Items items) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
//        this.sum = price.multiply(quantity);
        this.shipment = shipment;
        this.items = items;
    }

}