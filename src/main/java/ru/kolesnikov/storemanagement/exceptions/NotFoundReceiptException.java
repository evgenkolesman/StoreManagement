package ru.kolesnikov.storemanagement.exceptions;

public class NotFoundReceiptException extends RuntimeException {
    public NotFoundReceiptException(Long receiptId) {
        super(String.format("Receipt with ID: %s was not find", receiptId));
    }
}
