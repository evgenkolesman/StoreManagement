package ru.kolesnikov.storemanagement.controller.dto.receipt;

import java.math.BigDecimal;
import java.time.Instant;

public record ReceiptDTOResponse(Long id,
                                 String number,
                                 Instant date,
                                 String stockName,
                                 String supplierName,
                                 BigDecimal total,
                                 String details
) {
};