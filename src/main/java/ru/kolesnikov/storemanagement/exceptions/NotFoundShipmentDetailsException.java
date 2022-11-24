package ru.kolesnikov.storemanagement.exceptions;

public class NotFoundShipmentDetailsException extends RuntimeException {
    public NotFoundShipmentDetailsException(Long detailsId) {
        super(String.format("Shipment details with ID: %s was not find", detailsId));

    }
}
