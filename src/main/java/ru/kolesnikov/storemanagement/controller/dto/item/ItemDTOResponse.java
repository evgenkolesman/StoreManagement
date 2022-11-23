package ru.kolesnikov.storemanagement.controller.dto.item;

import java.math.BigDecimal;

public record ItemDTOResponse(Long id,
                              String itemName,
                              String barcode,
                              BigDecimal price) {
}
