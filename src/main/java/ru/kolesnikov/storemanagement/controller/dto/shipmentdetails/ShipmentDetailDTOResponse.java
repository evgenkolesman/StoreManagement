package ru.kolesnikov.storemanagement.controller.dto.shipmentdetails;

import java.math.BigDecimal;

public record ShipmentDetailDTOResponse(Long id,
                                        BigDecimal quantity,
                                        BigDecimal price,
                                        BigDecimal sum,
                                        String itemsName,
                                        String barcode) {
}
