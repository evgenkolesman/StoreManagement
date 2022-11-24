package ru.kolesnikov.storemanagement.controller.dto.shipmentdetails;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record ShipmentDetailDTORequest(@NotBlank BigDecimal quantity,
                                       @NotBlank BigDecimal price,
                                       @NotBlank String barcode) {
}
