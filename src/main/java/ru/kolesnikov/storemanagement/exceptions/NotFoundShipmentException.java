package ru.kolesnikov.storemanagement.exceptions;

public class NotFoundShipmentException extends RuntimeException {
    public NotFoundShipmentException(Long receiptId) {
        super(String.format("Receipt with ID: %s was not find", receiptId));
    }
}
