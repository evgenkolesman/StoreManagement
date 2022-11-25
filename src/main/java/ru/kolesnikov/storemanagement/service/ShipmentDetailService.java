package ru.kolesnikov.storemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikov.storemanagement.exceptions.NotFoundShipmentDetailsException;
import ru.kolesnikov.storemanagement.model.Items;
import ru.kolesnikov.storemanagement.model.Shipment;
import ru.kolesnikov.storemanagement.model.ShipmentDetail;
import ru.kolesnikov.storemanagement.repository.ShipmentDetailRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ShipmentDetailService {

    private final ShipmentDetailRepository shipmentDetailRepository;
    private final ItemService itemService;

    public ShipmentDetail addShipmentDetails(Shipment shipment,
                                             BigInteger quantity,
                                             BigDecimal price,
                                             String barcode) {


        AtomicReference<Items> items = new AtomicReference<>(itemService.getItemByBarcode(barcode));
//        if(items.get().getBalance().subtract(quantity)
        items.updateAndGet(i -> {
            i.setBalance(items.get().getBalance().subtract(quantity));
            return i;
        });

        itemService.updateItem(items.get().getId(), items.get());
        return shipmentDetailRepository.save(new ShipmentDetail(
                shipment,
                quantity,
                price,
                items.get()));
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
