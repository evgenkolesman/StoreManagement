package ru.kolesnikov.storemanagement.exceptions;

public class NotFoundStockException extends RuntimeException {
    public NotFoundStockException(Long stockid) {
        super("This stock was added " + stockid);
    }
}
