package ru.kolesnikov.storemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikov.storemanagement.controller.dto.shipment.ShipmentDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.shipment.ShipmentDTOResponse;
import ru.kolesnikov.storemanagement.controller.dto.shipmentdetails.ShipmentDetailDTOResponse;
import ru.kolesnikov.storemanagement.model.Shipment;
import ru.kolesnikov.storemanagement.model.ShipmentDetail;
import ru.kolesnikov.storemanagement.service.LegalEntityService;
import ru.kolesnikov.storemanagement.service.ShipmentService;
import ru.kolesnikov.storemanagement.service.StockService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;
    private final StockService stockService;
    private final LegalEntityService legalEntityService;

    @PostMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/shipment")
    public Shipment addShipment(@PathVariable("stockId") Long stockId,
                                @PathVariable("entityId") Long entityId,
                                @RequestBody ShipmentDTORequest shipmentDTORequest) {
        return shipmentService.addShipment(new Shipment(shipmentDTORequest.number(),
                Instant.now(),
                stockService.getStockById(stockId),
                legalEntityService.getLegalEntityById(entityId),
                shipmentDTORequest.details()));

    }

    @PutMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/shipment/{shipmentId}")
    public Shipment updateShipment(@PathVariable("stockId") Long stockId,
                                   @PathVariable("entityId") Long entityId,
                                   @PathVariable("shipmentId") Long shipmentId,
                                   @RequestBody ShipmentDTORequest shipmentDTORequest) {
        return shipmentService.updateShipment(new Shipment(shipmentId,
                shipmentDTORequest.number(),
                Instant.now(),
                stockService.getStockById(stockId),
                legalEntityService.getLegalEntityById(entityId),
                shipmentDTORequest.details()));

    }

    @DeleteMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/shipment/{shipmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShipment(@PathVariable("shipmentId") Long shipmentId) {
        shipmentService.deleteShipment(shipmentId);

    }

    @GetMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/shipment/{shipmentId}")
    public ShipmentDTOResponse getShipmentById(@PathVariable("stockId") Long stockId,
                                               @PathVariable("entityId") Long entityId,
                                               @PathVariable("shipmentId") Long shipmentId) {
        Shipment shipment = shipmentService.getShipmentById(stockId, entityId, shipmentId);
        return new ShipmentDTOResponse(shipment.getId(),
                shipment.getNumber(),
                shipment.getDate(),
                shipment.getStock().getStockName(),
                shipment.getSupplies().getEntityName(),
                shipment.getShipmentDetails().stream()
                        .map(shipmentDetail -> new ShipmentDetailDTOResponse(
                                shipmentDetail.getId(),
                                shipmentDetail.getQuantity(),
                                shipmentDetail.getPrice(),
                                shipmentDetail.getSum(),
                                shipmentDetail.getItems().getItemName(),
                                shipmentDetail.getItems().getBarcode()))
                        .collect(Collectors.toList()),
                shipment.getShipmentDetails().stream()
                        .map(ShipmentDetail::getSum)
                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                shipment.getDetails());

    }


}
