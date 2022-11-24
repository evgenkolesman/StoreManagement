package ru.kolesnikov.storemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikov.storemanagement.exceptions.NotFoundShipmentException;
import ru.kolesnikov.storemanagement.model.Shipment;
import ru.kolesnikov.storemanagement.repository.ShipmentRepository;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final StockService stockService;
    private final LegalEntityService legalEntityService;


    public Shipment addShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Shipment getShipmentById(Long stockId, Long entityId, Long shipmentId) {
        if (!shipmentRepository.existsReceiptByStockAndSuppliesAndId(
                stockService.getStockById(stockId),
                legalEntityService.getLegalEntityById(entityId),
                shipmentId)) {
            throw new NotFoundShipmentException(shipmentId);
        }
        return shipmentRepository.getReferenceById(shipmentId);
    }

    public void deleteShipment(Long shipmentId) {
        shipmentRepository.deleteById(shipmentId);
    }

}
