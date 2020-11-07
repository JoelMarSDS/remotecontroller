package com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.exceptionhandler;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.BusinessException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFundException;
import com.cuscuzcomjava.remotecontroller.configuration.util.messageproperties.PropertiesSourceMessange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.rmi.activation.ActivationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFundException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
            EntityNotFundException e) {
        Problems problems = new Problems();
        problems.setTimestamp(LocalDateTime.now());
        problems.setUserMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problems);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
            ConflictException e) {
        Problems problems = new Problems();
        problems.setTimestamp(LocalDateTime.now());
        problems.setUserMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(problems);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> tratarNegocioException(BusinessException e) {
        Problems problems = new Problems();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problems);
    }

}
