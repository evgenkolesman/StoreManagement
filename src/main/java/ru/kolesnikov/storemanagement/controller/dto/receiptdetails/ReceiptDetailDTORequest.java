package ru.kolesnikov.storemanagement.controller.dto.receiptdetails;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record ReceiptDetailDTORequest(@NotBlank BigDecimal quantity,
                                      @NotBlank BigDecimal price,
                                      @NotBlank String barcode) {
}
