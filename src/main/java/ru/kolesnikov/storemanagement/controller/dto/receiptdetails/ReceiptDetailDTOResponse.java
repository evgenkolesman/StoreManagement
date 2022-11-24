package ru.kolesnikov.storemanagement.controller.dto.receiptdetails;

import java.math.BigDecimal;

public record ReceiptDetailDTOResponse(Long id,
                                       BigDecimal quantity,
                                       BigDecimal price,
                                       BigDecimal sum,
                                       String itemsName,
                                       String barcode) {
}
