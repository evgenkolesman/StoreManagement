package ru.kolesnikov.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolesnikov.storemanagement.model.ReceiptDetail;

import java.util.List;
import java.util.Optional;

public interface ReceiptDetailRepository extends JpaRepository<ReceiptDetail, Long> {
    Optional<ReceiptDetail> getReceiptDetailById(Long id);

    List<ReceiptDetail> getReceiptDetailsByReceiptId(Long id);
}
