package ru.kolesnikov.storemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikov.storemanagement.exceptions.NotFoundReceiptDetailsException;
import ru.kolesnikov.storemanagement.model.Items;
import ru.kolesnikov.storemanagement.model.Receipt;
import ru.kolesnikov.storemanagement.model.ReceiptDetail;
import ru.kolesnikov.storemanagement.repository.ReceiptDetailRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ReceiptDetailService {
    private final ReceiptDetailRepository receiptDetailRepository;
    private final ItemService itemService;

    public ReceiptDetail addReceiptDetails(Receipt receipt,
                                           BigInteger quantity,
                                           BigDecimal price,
                                           String barcode) {
        AtomicReference<Items> items = new AtomicReference<>(itemService.getItemByBarcode(barcode));
        items.updateAndGet(i -> {
            i.setBalance(items.get().getBalance().add(quantity));
            return i;
        });

        itemService.updateItem(items.get().getId(), items.get());
        return receiptDetailRepository.save(new ReceiptDetail(
                quantity,
                price,
                receipt,
                items.get()
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
                                              BigInteger quantity,
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

    public List<ReceiptDetail> getReceiptDetailsByReceiptID(Long receiptId) {
        return receiptDetailRepository.getReceiptDetailsByReceiptId(receiptId);
    }
}
