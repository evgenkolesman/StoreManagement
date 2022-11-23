package ru.kolesnikov.storemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolesnikov.storemanagement.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
