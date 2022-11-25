package ru.kolesnikov.storemanagement.controller.dto.shipmentdetails;

import java.math.BigDecimal;
import java.math.BigInteger;

public record ShipmentDetailDTOResponse(Long id,
                                        BigInteger quantity,
                                        BigDecimal price,
                                        BigDecimal sum,
                                        String itemsName,
                                        String barcode) {
}
