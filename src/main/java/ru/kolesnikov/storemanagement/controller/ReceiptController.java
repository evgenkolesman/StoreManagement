package ru.kolesnikov.storemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikov.storemanagement.controller.dto.receipt.ReceiptDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.receipt.ReceiptDTOResponse;
import ru.kolesnikov.storemanagement.controller.dto.receiptdetails.ReceiptDetailDTOResponse;
import ru.kolesnikov.storemanagement.model.Receipt;
import ru.kolesnikov.storemanagement.model.ReceiptDetail;
import ru.kolesnikov.storemanagement.service.LegalEntityService;
import ru.kolesnikov.storemanagement.service.ReceiptService;
import ru.kolesnikov.storemanagement.service.StockService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ReceiptController {

//    private static final String URI_DETAILS = "/api/v1/stock/%s/legalEntity/%s/receipt/%s/details";
//    private static final String HTTP_LOCALHOST = "http://localhost";

    @Value("${local.server.port}")
    private String PORT;

    private final ReceiptService receiptService;
    private final StockService stockService;
    private final LegalEntityService legalEntityService;
//    private final RestTemplate client;

    @PostMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt")
    public Receipt addReceipt(@PathVariable("stockId") Long stockId,
                              @PathVariable("entityId") Long entityId,
                              @RequestBody ReceiptDTORequest receiptDTORequest) {
        return receiptService.addReceipt(new Receipt(
                receiptDTORequest.number(),
                Instant.now(),
                stockService.getStockById(stockId),
                legalEntityService.getLegalEntityById(entityId),
                receiptDTORequest.details()));

    }

    @PutMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}")
    public Receipt updateReceipt(@PathVariable("stockId") Long stockId,
                                 @PathVariable("entityId") Long entityId,
                                 @PathVariable("receiptId") Long receiptId,
                                 @RequestBody ReceiptDTORequest receiptDTORequest) {
        return receiptService.updateReceipt(new Receipt(
                receiptId,
                receiptDTORequest.number(),
                Instant.now(),
                stockService.getStockById(stockId),
                legalEntityService.getLegalEntityById(entityId),
                receiptDTORequest.details()));

    }

    @DeleteMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReceipt(@PathVariable("stockId") Long stockId,
                              @PathVariable("entityId") Long entityId,
                              @PathVariable("receiptId") Long receiptId) {
        receiptService.deleteReceipt(stockId, entityId, receiptId);

    }

    /**
     * This would return filled receipt document as JSON to frontend
     *
     * @param stockId
     * @param entityId
     * @param receiptId
     * @return ReceiptDTOResponse.class
     */
    @GetMapping("/api/v1/stock/{stockId}/legalEntity/{entityId}/receipt/{receiptId}")
    public ReceiptDTOResponse getReceiptById(@PathVariable("stockId") Long stockId,
                                             @PathVariable("entityId") Long entityId,
                                             @PathVariable("receiptId") Long receiptId) {
        Receipt receipt = receiptService.getReceiptById(stockId, entityId, receiptId);
//        List<ReceiptDetailDTOResponse> forEntity = client.getForObject( //redundant it would be useful if it would be different services
//                UriComponentsBuilder
//                        .fromHttpUrl(HTTP_LOCALHOST)
//                        .port(PORT)
//                        .replacePath(String.format(URI_DETAILS,
//                        stockId,
//                        entityId,
//                        receiptId)).toUriString(),
//                ArrayList.class);
        return new ReceiptDTOResponse(receipt.getId(),
                receipt.getNumber(),
                receipt.getDate(),
                receipt.getStock().getStockName(),
                receipt.getSupplies().getEntityName(),
                receipt.getReceiptDetails().stream()
                        .map(receiptDetail -> new ReceiptDetailDTOResponse(
                                receiptDetail.getId(),
                                receiptDetail.getQuantity(),
                                receiptDetail.getPrice(),
                                receiptDetail.getSum(),
                                receiptDetail.getItems().getItemName(),
                                receiptDetail.getItems().getBarcode()
                        ))
                        .collect(Collectors.toList()), //list
                receipt.getReceiptDetails().stream()
                        .map(ReceiptDetail::getSum)
                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                receipt.getDetails());

    }


}
