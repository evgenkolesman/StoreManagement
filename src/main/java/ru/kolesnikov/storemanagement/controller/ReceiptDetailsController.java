package ru.kolesnikov.storemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.kolesnikov.storemanagement.controller.dto.receiptdetails.ReceiptDetailDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.receiptdetails.ReceiptDetailDTOResponse;
import ru.kolesnikov.storemanagement.model.ReceiptDetail;
import ru.kolesnikov.storemanagement.service.ReceiptDetailService;
import ru.kolesnikov.storemanagement.service.ReceiptService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ReceiptDetailsController {

    private final ReceiptDetailService receiptDetailService;
    private final ReceiptService receiptService;
    private final RestTemplate client;

    @PostMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}/details")
    public ReceiptDetailDTOResponse addReceiptDetail(@PathVariable("stockId") Long stockId,
                                                     @PathVariable("entityId") Long entityId,
                                                     @PathVariable("receiptId") Long receiptId,
                                                     @RequestBody ReceiptDetailDTORequest receiptDetailDTORequest) {
        ReceiptDetail receiptDetail = receiptDetailService.addReceiptDetails(
                receiptService.getReceiptById(stockId, entityId, receiptId),
                receiptDetailDTORequest.quantity(),
                receiptDetailDTORequest.price(),
                receiptDetailDTORequest.barcode());
        return new ReceiptDetailDTOResponse(
                receiptDetail.getId(),
                receiptDetail.getQuantity(),
                receiptDetail.getPrice(),
                receiptDetail.getSum(),
                receiptDetail.getItems().getItemName(),
                receiptDetail.getItems().getBarcode()
        );

    }

    @GetMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}/details")
    public List<ReceiptDetailDTOResponse> addReceiptDetail(@PathVariable("receiptId") Long receiptId) {
        List<ReceiptDetail> detailList = receiptDetailService.getReceiptDetailsByReceiptID(receiptId);
        return detailList.stream()
                .map(receiptDetail -> new ReceiptDetailDTOResponse(
                        receiptDetail.getId(),
                        receiptDetail.getQuantity(),
                        receiptDetail.getPrice(),
                        receiptDetail.getSum(),
                        receiptDetail.getItems().getItemName(),
                        receiptDetail.getItems().getBarcode()
                ))
                .collect(Collectors.toList());


    }

    @PutMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}/details/{detailsId}")
    public ReceiptDetailDTOResponse updateReceiptDetails(@PathVariable("stockId") Long stockId,
                                                         @PathVariable("entityId") Long entityId,
                                                         @PathVariable("receiptId") Long receiptId,
                                                         @PathVariable("detailsId") Long detailsId,
                                                         @RequestBody ReceiptDetailDTORequest receiptDetailDTORequest) {
        ReceiptDetail receiptDetail = receiptDetailService.updateReceiptDetails(receiptService.getReceiptById(stockId, entityId, receiptId),
                detailsId,
                receiptDetailDTORequest.quantity(),
                receiptDetailDTORequest.price(),
                receiptDetailDTORequest.barcode());
        return new ReceiptDetailDTOResponse(
                receiptDetail.getId(),
                receiptDetail.getQuantity(),
                receiptDetail.getPrice(),
                receiptDetail.getSum(),
                receiptDetail.getItems().getItemName(),
                receiptDetail.getItems().getBarcode()
        );
    }

    @DeleteMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}/details/{detailsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReceipt(@PathVariable("detailsId") Long detailsId) {
        receiptDetailService.deleteReceiptDetails(detailsId);

    }

    @GetMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}/details/{detailsId}")
    public ReceiptDetailDTOResponse getReceiptById(@PathVariable("detailsId") Long detailsId) {
        ReceiptDetail receiptDetails = receiptDetailService.getReceiptDetailsById(detailsId);
        return new ReceiptDetailDTOResponse(
                receiptDetails.getId(),
                receiptDetails.getQuantity(),
                receiptDetails.getPrice(),
                receiptDetails.getSum(),
                receiptDetails.getItems().getItemName(),
                receiptDetails.getItems().getBarcode()
        );
    }


}
