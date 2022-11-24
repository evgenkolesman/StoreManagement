package ru.kolesnikov.storemanagement.controller.dto.shipment;

import javax.validation.constraints.NotBlank;

public record ShipmentDTORequest(@NotBlank String number, String details) {
}
