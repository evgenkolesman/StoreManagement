package ru.kolesnikov.storemanagement.exceptions.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kolesnikov.storemanagement.exceptions.*;
import ru.kolesnikov.storemanagement.exceptions.model.ErrorModel;

import java.util.UUID;

@ControllerAdvice
@Slf4j
public class SMExceptionHandler {

    @ExceptionHandler(
            {NotFoundItemException.class,
                    NotFoundLegalEntityException.class,
                    NotFoundReceiptDetailsException.class,
                    NotFoundReceiptException.class,
                    NotFoundShipmentDetailsException.class,
                    NotFoundShipmentException.class,
                    NotFoundStockException.class}
    )
    protected ResponseEntity<ErrorModel> handleException(Exception exception) {
        var errorId = UUID.randomUUID().toString();
        log.info(String.format("%s %s", errorId, exception.getMessage()));
        return new ResponseEntity<>(new ErrorModel(errorId,
                exception.getMessage(),
                HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorModel> handleException(NotEnoughBalanceException exception) {
        var errorId = UUID.randomUUID().toString();
        log.info(String.format("%s %s", errorId, exception.getMessage()));
        return new ResponseEntity<>(new ErrorModel(errorId,
                exception.getMessage(),
                HttpStatus.PRECONDITION_FAILED),
                HttpStatus.PRECONDITION_FAILED);
    }


}
