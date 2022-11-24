package ru.kolesnikov.storemanagement.controller.dto.receipt;

import javax.validation.constraints.NotBlank;

public record ReceiptDTORequest(@NotBlank String number, String details) {
}
