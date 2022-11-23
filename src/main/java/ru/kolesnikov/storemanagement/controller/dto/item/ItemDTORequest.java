package ru.kolesnikov.storemanagement.controller.dto.item;

import lombok.NonNull;

import java.math.BigDecimal;

public record ItemDTORequest(@NonNull String itemName,
                             @NonNull String barcode,
                             @NonNull BigDecimal price) {
}
