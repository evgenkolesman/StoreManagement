package ru.kolesnikov.storemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikov.storemanagement.exceptions.NotFoundStockException;
import ru.kolesnikov.storemanagement.model.Stock;
import ru.kolesnikov.storemanagement.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;


    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStock(Long stockId, Stock stock) {
        if (stockRepository.findById(stockId).isEmpty()) {
            throw new NotFoundStockException(stockId);
        }
        return stockRepository.save(stock);
    }

    public void delete(Long stockId) {
        if (stockRepository.findById(stockId).isEmpty()) {
            throw new NotFoundStockException(stockId);
        }
        stockRepository.deleteById(stockId);
    }

    public Stock getStockById(Long stockId) {
        return stockRepository.findById(stockId).orElseThrow(() -> new NotFoundStockException(stockId));
    }
}
