package ru.kolesnikov.storemanagement.controller.dto.receiptdetails;

import java.math.BigDecimal;
import java.math.BigInteger;

public record ReceiptDetailDTOResponse(Long id,
                                       BigInteger quantity,
                                       BigDecimal price,
                                       BigDecimal sum,
                                       String itemsName,
                                       String barcode) {
}
