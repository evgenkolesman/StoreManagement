package ru.kolesnikov.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolesnikov.storemanagement.model.ShipmentDetail;

import java.util.Optional;

@Repository
public interface ShipmentDetailRepository extends JpaRepository<ShipmentDetail, Long> {
    Optional<ShipmentDetail> getShipmentDetailsById(Long detailsId);
}
