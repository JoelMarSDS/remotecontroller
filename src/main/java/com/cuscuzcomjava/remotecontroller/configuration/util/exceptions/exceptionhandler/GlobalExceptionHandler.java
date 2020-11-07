package com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.exceptionhandler;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.BusinessException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFoundException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> dealWithEntityNotFoundException(EntityNotFoundException e) {
        Problems problems = new Problems();
        problems.setTimestamp(LocalDateTime.now());
        problems.setUserMessage(e.getMessage());
        problems.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problems);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> dealWithConflictException(ConflictException e) {
        Problems problems = new Problems();
        problems.setTimestamp(LocalDateTime.now());
        problems.setUserMessage(e.getMessage());
        problems.setStatus(HttpStatus.CONFLICT.value());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(problems);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> dealWithBusinessException (BusinessException e) {
        Problems problems = new Problems();
        problems.setTimestamp(LocalDateTime.now());
        problems.setUserMessage(e.getMessage());
        problems.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problems);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> dealWithGenericException (Exception e) {
        Problems problems = new Problems();
        problems.setTimestamp(LocalDateTime.now());
        problems.setUserMessage(e.getMessage());
        problems.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(problems);
    }
}
