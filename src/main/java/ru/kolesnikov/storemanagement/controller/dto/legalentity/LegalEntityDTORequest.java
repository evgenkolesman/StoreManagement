package ru.kolesnikov.storemanagement.controller.dto.legalentity;

import lombok.NonNull;

import javax.validation.constraints.Size;

public record LegalEntityDTORequest(@NonNull String name, String address, @NonNull @Size(min = 9, max = 9) String inn) {
}
