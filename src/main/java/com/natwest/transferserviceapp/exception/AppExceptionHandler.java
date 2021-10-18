package com.natwest.transferserviceapp.exception;

import com.natwest.transferserviceapp.model.ErrorResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Custom Exception Handler for TransferService Application.
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> genericException(final Exception ex, final WebRequest request) {

        String  message =  ex.getMessage();

        ResponseEntity<Object> respObj = new ResponseEntity<>(ErrorResponseMessage.builder().message(message)
                .timestamp(new Date()).build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        return respObj;
    }
}
