package ru.kolesnikov.storemanagement.controller.dto.shipment;

import ru.kolesnikov.storemanagement.controller.dto.shipmentdetails.ShipmentDetailDTOResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ShipmentDTOResponse(Long id,
                                  String number,
                                  Instant date,
                                  String stockName,
                                  String supplierName,
                                  List<ShipmentDetailDTOResponse> detailsList,
                                  BigDecimal total,
                                  String details
) {
}