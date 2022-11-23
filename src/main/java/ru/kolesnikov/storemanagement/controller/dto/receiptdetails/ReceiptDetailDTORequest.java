package ru.kolesnikov.storemanagement.controller.dto.receiptdetails;

import java.math.BigDecimal;

public record ReceiptDetailDTORequest(BigDecimal quantity,
                                      BigDecimal price,
                                      String barcode) {
}
