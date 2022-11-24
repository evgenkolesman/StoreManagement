package ru.kolesnikov.storemanagement.controller.dto.receipt;

import ru.kolesnikov.storemanagement.controller.dto.receiptdetails.ReceiptDetailDTOResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

//@JsonFormat
public record ReceiptDTOResponse(Long id,
                                 String number,
                                 Instant date,
                                 String stockName,
                                 String supplierName,
//                                 @JsonFormat(shape = JsonFormat.Shape.ARRAY)
                                 List<ReceiptDetailDTOResponse> detailList,
                                 BigDecimal total,
                                 String details
) {
};