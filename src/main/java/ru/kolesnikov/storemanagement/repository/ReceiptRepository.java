package ru.kolesnikov.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolesnikov.storemanagement.model.LegalEntity;
import ru.kolesnikov.storemanagement.model.Receipt;
import ru.kolesnikov.storemanagement.model.Stock;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    boolean existsReceiptByStockAndSuppliesAndId(Stock stock,
                                                 LegalEntity legalEntity,
                                                 Long id);
}
