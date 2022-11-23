package ru.kolesnikov.storemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikov.storemanagement.controller.dto.receipt.ReceiptDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.receipt.ReceiptDTOResponse;
import ru.kolesnikov.storemanagement.model.Receipt;
import ru.kolesnikov.storemanagement.model.ReceiptDetail;
import ru.kolesnikov.storemanagement.service.ReceiptService;

import java.math.BigDecimal;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}")
    public Receipt addReceipt(@PathVariable("stockId") Long stockId,
                              @PathVariable("entityId") Long entityId,
                              @RequestBody ReceiptDTORequest receiptDTORequest) {
        return receiptService.addReceipt(stockId, entityId, receiptDTORequest);

    }

    @PutMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}")
    public Receipt updateReceipt(@PathVariable("stockId") Long stockId,
                                 @PathVariable("entityId") Long entityId,
                                 @PathVariable("receiptId") Long receiptId,
                                 @RequestBody ReceiptDTORequest receiptDTORequest) {
        return receiptService.updateReceipt(stockId, entityId, receiptId, receiptDTORequest);

    }

    @DeleteMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReceipt(@PathVariable("stockId") Long stockId,
                              @PathVariable("entityId") Long entityId,
                              @PathVariable("receiptId") Long receiptId) {
        receiptService.deleteReceipt(stockId, entityId, receiptId);

    }

    @GetMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ReceiptDTOResponse getReceiptById(@PathVariable("stockId") Long stockId,
                                             @PathVariable("entityId") Long entityId,
                                             @PathVariable("receiptId") Long receiptId) {
        Receipt receipt = receiptService.getReceiptById(stockId, entityId, receiptId);
        return new ReceiptDTOResponse(receipt.getId(),
                receipt.getNumber(),
                receipt.getDate(),
                receipt.getStock().getStockName(),
                receipt.getSupplies().getEntityName(),
                receipt.getTotal().stream()
                        .map(ReceiptDetail::getSum)
                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                receipt.getDetails());

    }


}
