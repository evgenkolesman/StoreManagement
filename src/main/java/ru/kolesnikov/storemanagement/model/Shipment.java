package ru.kolesnikov.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "shipment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "date")
    private Instant date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "legal_entity_id")
    private LegalEntity supplies;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shipment", cascade = CascadeType.ALL)
    //TODO make this calculations
    private List<ShipmentDetail> shipmentDetails;

    private String details;


    public Shipment(String number,
                    Instant date,
                    Stock stock,
                    LegalEntity supplies,
                    String details) {
        this.number = number;
        this.date = date;
        this.stock = stock;
        this.supplies = supplies;
        this.details = details;
    }

    public Shipment(Long id,
                    String number,
                    Instant date,
                    Stock stock,
                    LegalEntity supplies,
                    String details) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.stock = stock;
        this.supplies = supplies;
        this.details = details;
    }
}
