package ru.kolesnikov.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stocks")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock_name", length = 100, nullable = false)
    private String stockName;
    @Column(name = "address", length = 150)
    private String address;

    public Stock(String stockName, String address) {
        this.stockName = stockName;
        this.address = address;
    }
}
