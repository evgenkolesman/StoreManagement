package ru.kolesnikov.storemanagement.controller.dto.receiptdetails;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.BigInteger;

public record ReceiptDetailDTORequest(@NotBlank BigInteger quantity,
                                      @NotBlank BigDecimal price,
                                      @NotBlank String barcode) {
}
