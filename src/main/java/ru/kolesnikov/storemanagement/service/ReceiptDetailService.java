package ru.kolesnikov.storemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikov.storemanagement.exceptions.NotFoundReceiptDetailsException;
import ru.kolesnikov.storemanagement.model.Items;
import ru.kolesnikov.storemanagement.model.Receipt;
import ru.kolesnikov.storemanagement.model.ReceiptDetail;
import ru.kolesnikov.storemanagement.repository.ReceiptDetailRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReceiptDetailService {
    private final ReceiptDetailRepository receiptDetailRepository;
    private final ItemService itemService;

    public ReceiptDetail addReceiptDetails(Receipt receipt,
                                           BigDecimal quantity,
                                           BigDecimal price,
                                           String barcode) {
        Items items = itemService.getItemByBarcode(barcode);
        return receiptDetailRepository.save(new ReceiptDetail(
                quantity,
                price,
                receipt,
                items
        ));
    }

    public void deleteReceiptDetails(Long detailsId) {
        receiptDetailRepository.delete(getReceiptDetailsById(detailsId));
    }

    public ReceiptDetail getReceiptDetailsById(Long detailsId) {
        return receiptDetailRepository.getReceiptDetailById(detailsId)
                .orElseThrow(() -> new NotFoundReceiptDetailsException(detailsId));
    }

    public ReceiptDetail updateReceiptDetails(Receipt receipt,
                                              Long detailsId,
                                              BigDecimal quantity,
                                              BigDecimal price,
                                              String barcode) {

        Items items = itemService.getItemByBarcode(barcode);
        return receiptDetailRepository.save(new ReceiptDetail(
                detailsId,
                quantity,
                price,
                receipt,
                items
        ));
    }
}