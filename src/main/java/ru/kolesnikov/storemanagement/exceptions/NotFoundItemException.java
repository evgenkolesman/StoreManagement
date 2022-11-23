package ru.kolesnikov.storemanagement.exceptions;

public class NotFoundItemException extends RuntimeException {
    public NotFoundItemException(Long id) {
        super(String.format("This item with id %s not found", id));
    }


    public NotFoundItemException(String barcode) {
        super(String.format("This item with barcode %s not found", barcode));
    }
}
