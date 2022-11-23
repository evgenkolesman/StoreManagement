package ru.kolesnikov.storemanagement.controller.dto.stock;

import lombok.NonNull;

public record StockDTORequest(@NonNull String stockName,
                              String address) {

}
