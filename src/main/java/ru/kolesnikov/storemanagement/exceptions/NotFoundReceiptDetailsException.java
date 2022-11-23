package ru.kolesnikov.storemanagement.exceptions;

public class NotFoundReceiptDetailsException extends RuntimeException {
    public NotFoundReceiptDetailsException(Long receiptId) {
        super(String.format("Receipt details with ID: %s was not find", receiptId));
    }
}
