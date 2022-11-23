package ru.kolesnikov.storemanagement.exceptions;

public class NotFoundLegalEntityException extends RuntimeException {
    public NotFoundLegalEntityException(Long id) {
        super(String.format("This legal entity with id %s not found", id));
    }
}
