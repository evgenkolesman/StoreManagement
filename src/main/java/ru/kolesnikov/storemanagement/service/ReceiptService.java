package ru.kolesnikov.storemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikov.storemanagement.controller.dto.receipt.ReceiptDTORequest;
import ru.kolesnikov.storemanagement.exceptions.NotFoundReceiptException;
import ru.kolesnikov.storemanagement.model.Receipt;
import ru.kolesnikov.storemanagement.repository.ReceiptRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final StockService stockService;
    private final LegalEntityService legalEntityService;

    public Receipt addReceipt(Long stockId, Long entityId, ReceiptDTORequest receiptDTORequest) {

        return receiptRepository.save(new Receipt(receiptDTORequest.number(),
                Instant.now(),
                stockService.getStockById(stockId),
                legalEntityService.getLegalEntityById(entityId),
                receiptDTORequest.details()));
    }

    public Receipt updateReceipt(Long stockId,
                                 Long entityId,
                                 Long receiptId,
                                 ReceiptDTORequest receiptDTORequest) {
        Receipt receipt = receiptRepository.findById(receiptId)
                .orElseThrow(() -> new NotFoundReceiptException(receiptId));

        return receiptRepository.save(new Receipt(receipt.getId(),
                receiptDTORequest.number(),
                Instant.now(),
                stockService.getStockById(stockId),
                legalEntityService.getLegalEntityById(entityId),
                receipt.getTotal(),
                receiptDTORequest.details()));
    }

    public void deleteReceipt(Long stockId, Long entityId, Long receiptId) {
        if (receiptRepository.existsReceiptByStockAndSuppliesAndId(
                stockService.getStockById(stockId),
                legalEntityService.getLegalEntityById(entityId),
                receiptId)) {
            throw new NotFoundReceiptException(receiptId);
        }
        receiptRepository.deleteById(receiptId);
    }

    public Receipt getReceiptById(Long stockId, Long entityId, Long receiptId) {
        if (receiptRepository.existsReceiptByStockAndSuppliesAndId(
                stockService.getStockById(stockId),
                legalEntityService.getLegalEntityById(entityId),
                receiptId)) {
            throw new NotFoundReceiptException(receiptId);
        }
        return receiptRepository
                .findById(receiptId)
                .orElseThrow(() -> new NotFoundReceiptException(receiptId));
    }


}
