package ru.kolesnikov.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "legal_entity")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LegalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "entity_name", length = 100, nullable = false)
    String entityName;

    @Column(name = "address", length = 150)
    String address;

    @Column(name = "inn", length = 9, unique = true)
    String inn;

    public LegalEntity(String entityName, String address, String inn) {
        this.entityName = entityName;
        this.address = address;
        this.inn = inn;
    }
}
