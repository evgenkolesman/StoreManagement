package ru.kolesnikov.storemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikov.storemanagement.exceptions.NotFoundShipmentDetailsException;
import ru.kolesnikov.storemanagement.model.ShipmentDetail;
import ru.kolesnikov.storemanagement.repository.ShipmentDetailRepository;

@Service
@RequiredArgsConstructor
public class ShipmentDetailService {

    private final ShipmentDetailRepository shipmentDetailRepository;

    public ShipmentDetail addShipmentDetails(ShipmentDetail shipmentDetail) {
        return shipmentDetailRepository.save(shipmentDetail);
    }

    public ShipmentDetail updateShipmentDetails(ShipmentDetail shipmentDetail) {
        return shipmentDetailRepository.save(shipmentDetail);
    }

    public ShipmentDetail getShipmentDetailsById(Long detailsId) {
        return shipmentDetailRepository.getShipmentDetailsById(detailsId)
                .orElseThrow(() -> new NotFoundShipmentDetailsException(detailsId));
    }

    public void deleteReceiptDetails(Long detailsId) {
        shipmentDetailRepository.deleteById(detailsId);
    }
}
