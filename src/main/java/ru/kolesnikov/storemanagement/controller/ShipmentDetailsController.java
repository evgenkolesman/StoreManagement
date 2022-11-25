package ru.kolesnikov.storemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikov.storemanagement.controller.dto.shipmentdetails.ShipmentDetailDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.shipmentdetails.ShipmentDetailDTOResponse;
import ru.kolesnikov.storemanagement.model.Items;
import ru.kolesnikov.storemanagement.model.ShipmentDetail;
import ru.kolesnikov.storemanagement.service.ItemService;
import ru.kolesnikov.storemanagement.service.ShipmentDetailService;
import ru.kolesnikov.storemanagement.service.ShipmentService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ShipmentDetailsController {

    private final ShipmentDetailService shipmentDetailService;
    private final ShipmentService shipmentService;
    private final ItemService itemsService;


    @PostMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/shipment/{shipmentId}/details")
    public ShipmentDetailDTOResponse addReceiptDetail(@PathVariable("stockId") Long stockId,
                                                      @PathVariable("entityId") Long entityId,
                                                      @PathVariable("shipmentId") Long shipmentId,
                                                      @RequestBody ShipmentDetailDTORequest shipmentDTORequest) {

        ShipmentDetail shipmentDetail = shipmentDetailService.addShipmentDetails(
                shipmentService.getShipmentById(stockId, entityId, shipmentId),
                shipmentDTORequest.quantity(),
                shipmentDTORequest.price(),
                shipmentDTORequest.barcode());
        return new ShipmentDetailDTOResponse(
                shipmentDetail.getId(),
                shipmentDetail.getQuantity(),
                shipmentDetail.getPrice(),
                shipmentDetail.getSum(),
                shipmentDetail.getItems().getItemName(),
                shipmentDetail.getItems().getBarcode()
        );

    }

    @PutMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/shipment/{shipmentId}/details/{detailsId}")
    public ShipmentDetailDTOResponse updateReceiptDetails(@PathVariable("stockId") Long stockId,
                                                          @PathVariable("entityId") Long entityId,
                                                          @PathVariable("shipmentId") Long shipmentId,
                                                          @PathVariable("detailsId") Long detailsId,
                                                          @RequestBody ShipmentDetailDTORequest shipmentDTORequest) {
        ShipmentDetail shipmentDetail = shipmentDetailService.updateShipmentDetails(new ShipmentDetail(
                detailsId,
                shipmentService.getShipmentById(shipmentId, stockId, entityId),
                shipmentDTORequest.quantity(),
                shipmentDTORequest.price(),
                itemsService.getItemByBarcode(shipmentDTORequest.barcode())));
        return new ShipmentDetailDTOResponse(
                shipmentDetail.getId(),
                shipmentDetail.getQuantity(),
                shipmentDetail.getPrice(),
                shipmentDetail.getSum(),
                shipmentDetail.getItems().getItemName(),
                shipmentDetail.getItems().getBarcode()
        );
    }

    @DeleteMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/shipment/{shipmentId}/details/{detailsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReceipt(@PathVariable("detailsId") Long detailsId) {
        shipmentDetailService.deleteReceiptDetails(detailsId);

    }

    @GetMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/shipment/{shipmentId}/details/{detailsId}")
    public ShipmentDetailDTOResponse getReceiptById(@PathVariable("detailsId") Long detailsId) {
        ShipmentDetail shipmentDetail = shipmentDetailService.getShipmentDetailsById(detailsId);
        return new ShipmentDetailDTOResponse(
                shipmentDetail.getId(),
                shipmentDetail.getQuantity(),
                shipmentDetail.getPrice(),
                shipmentDetail.getSum(),
                shipmentDetail.getItems().getItemName(),
                shipmentDetail.getItems().getBarcode()
        );
    }


}
