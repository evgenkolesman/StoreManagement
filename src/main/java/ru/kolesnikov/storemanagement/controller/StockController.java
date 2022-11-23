package ru.kolesnikov.storemanagement.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikov.storemanagement.controller.dto.stock.StockDTORequest;
import ru.kolesnikov.storemanagement.controller.dto.stock.StockDTOResponse;
import ru.kolesnikov.storemanagement.model.Stock;
import ru.kolesnikov.storemanagement.service.StockService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/api/v1/stock")
    public List<StockDTOResponse> getStocks() {
        return stockService.getStocks().stream()
                .map(stock -> new StockDTOResponse(stock.getId(), stock.getStockName(), stock.getAddress()))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/stock/{stockId}")
    public StockDTOResponse getStockById(@PathVariable("stockId") Long stockId) {
        Stock stock = stockService.getStockById(stockId);
        return new StockDTOResponse(stock.getId(), stock.getStockName(), stock.getAddress());

    }

    @PostMapping("/api/v1/stock")
    public StockDTOResponse addStock(@RequestBody
                                     @NonNull StockDTORequest stockDTORequest) {
        Stock stock = stockService.addStock(new Stock(stockDTORequest.stockName(),
                stockDTORequest.address()));
        return new StockDTOResponse(stock.getId(), stock.getStockName(), stock.getAddress());
    }

    @PutMapping("/api/v1/stock/{stockId}")
    public StockDTOResponse updateStock(@PathVariable("stockId") Long stockId,
                                        @RequestBody @NonNull
                                                StockDTORequest stockDTORequest) {
        Stock stock = stockService.updateStock(stockId, new Stock(stockId,
                stockDTORequest.stockName(),
                stockDTORequest.address()));
        return new StockDTOResponse(stock.getId(), stock.getStockName(), stock.getAddress());

    }

    @DeleteMapping("/api/v1/stock/{stockId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStock(@PathVariable("stockId") Long stockId) {
        stockService.delete(stockId);
    }


}
