package ru.kolesnikov.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolesnikov.storemanagement.model.LegalEntity;
import ru.kolesnikov.storemanagement.model.Shipment;
import ru.kolesnikov.storemanagement.model.Stock;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    boolean existsReceiptByStockAndSuppliesAndId(Stock stockById, LegalEntity legalEntityById, Long shipmentId);
}
