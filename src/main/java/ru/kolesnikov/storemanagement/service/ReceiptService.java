package ru.kolesnikov.storemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikov.storemanagement.exceptions.NotFoundReceiptException;
import ru.kolesnikov.storemanagement.model.Receipt;
import ru.kolesnikov.storemanagement.repository.ReceiptRepository;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final StockService stockService;
    private final LegalEntityService legalEntityService;

    public Receipt addReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public Receipt updateReceipt(Receipt receipt) {
        if (receiptRepository.findById(receipt.getId()).isEmpty()) {
            throw new NotFoundReceiptException(receipt.getId());
        }
        return receiptRepository.save(receipt);
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
        if (!receiptRepository.existsReceiptByStockAndSuppliesAndId(
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
