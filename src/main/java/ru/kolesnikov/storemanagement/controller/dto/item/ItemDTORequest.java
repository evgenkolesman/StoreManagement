package ru.kolesnikov.storemanagement.controller.dto.item;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record ItemDTORequest(@NotBlank String name,
                             @NotBlank String barcode,
                             @NotBlank BigDecimal price) {
}
