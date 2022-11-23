package ru.kolesnikov.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolesnikov.storemanagement.model.Items;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Items, Long> {

    Optional<Items> getItemByBarcode(String barcode);
}
