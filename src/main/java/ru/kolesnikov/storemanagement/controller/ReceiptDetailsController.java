package ru.kolesnikov.storemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikov.storemanagement.controller.dto.receiptdetails.ReceiptDetailDTORequest;
import ru.kolesnikov.storemanagement.model.ReceiptDetail;
import ru.kolesnikov.storemanagement.service.ReceiptDetailService;
import ru.kolesnikov.storemanagement.service.ReceiptService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ReceiptDetailsController {

    private final ReceiptDetailService receiptDetailService;
    private final ReceiptService receiptService;


    @PostMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}/details")
    public ReceiptDetail addReceiptDetail(@PathVariable("stockId") Long stockId,
                                          @PathVariable("entityId") Long entityId,
                                          @PathVariable("entityId") Long receiptId,
                                          @RequestBody ReceiptDetailDTORequest receiptDetailDTORequest) {
        return receiptDetailService.addReceiptDetails(
                receiptService.getReceiptById(stockId, entityId, receiptId),
                receiptDetailDTORequest.quantity(),
                receiptDetailDTORequest.price(),
                receiptDetailDTORequest.barcode());

    }

    @PutMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}/details/{detailsId}")
    public ReceiptDetail updateReceiptDetails(@PathVariable("stockId") Long stockId,
                                              @PathVariable("entityId") Long entityId,
                                              @PathVariable("receiptId") Long receiptId,
                                              @PathVariable("detailsId") Long detailsId,
                                              @RequestBody ReceiptDetailDTORequest receiptDetailDTORequest) {
        return receiptDetailService.updateReceiptDetails(receiptService.getReceiptById(stockId, entityId, receiptId),
                detailsId,
                receiptDetailDTORequest.quantity(),
                receiptDetailDTORequest.price(),
                receiptDetailDTORequest.barcode());

    }

    @DeleteMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}/details/{detailsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReceipt(@PathVariable("detailsId") Long detailsId) {
        receiptDetailService.deleteReceiptDetails(detailsId);

    }

    @GetMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}/details/{detailsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ReceiptDetail getReceiptById(@PathVariable("detailsId") Long detailsId) {
        return receiptDetailService.getReceiptDetailsById(detailsId);
    }


}
