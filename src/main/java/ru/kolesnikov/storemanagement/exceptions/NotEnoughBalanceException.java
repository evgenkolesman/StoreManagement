package ru.kolesnikov.storemanagement.exceptions;

public class NotEnoughBalanceException extends RuntimeException {

    public NotEnoughBalanceException() {
        super("There is not enough balance of this item");
    }
}
