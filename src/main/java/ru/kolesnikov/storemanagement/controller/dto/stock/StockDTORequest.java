package ru.kolesnikov.storemanagement.controller.dto.stock;

import javax.validation.constraints.NotBlank;

public record StockDTORequest(@NotBlank String stockName,
                              String address) {

}
