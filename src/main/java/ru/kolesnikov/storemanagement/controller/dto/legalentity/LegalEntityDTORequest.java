package ru.kolesnikov.storemanagement.controller.dto.legalentity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record LegalEntityDTORequest(@NotBlank String name,
                                    @NotBlank String address,
                                    @NotBlank @Size(min = 9, max = 9) String inn) {
}
