package ru.kolesnikov.storemanagement.controller.dto.shipmentdetails;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.BigInteger;

public record ShipmentDetailDTORequest(@NotBlank BigInteger quantity,
                                       @NotBlank BigDecimal price,
                                       @NotBlank String barcode) {
}
