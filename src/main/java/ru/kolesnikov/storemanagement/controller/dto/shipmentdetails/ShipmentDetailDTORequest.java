package ru.kolesnikov.storemanagement.controller.dto.shipmentdetails;

import java.math.BigDecimal;

public record ShipmentDetailDTORequest(BigDecimal quantity,
                                       BigDecimal price,
                                       String barcode) {
}
