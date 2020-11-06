package com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.exceptionhandler;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
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



    @ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = PropertiesSourceMessange.getMessageSource("error.msg.generic.user.final");

        ex.printStackTrace();

        Problems problems = createProblemBuilder(status, detail);

        return handleExceptionInternal(ex, problems, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> conflictException(ConflictException e, WebRequest request){

        HttpStatus status = HttpStatus.CONFLICT;
        String detail = "";


        Problems problems = createProblemBuilder(status, detail);
        return handleExceptionInternal(e, problems,  new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ActivationException.class)
    public ResponseEntity<?> activationException(ActivationException e, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = PropertiesSourceMessange.getMessageSource("actress.does.not.exists") + " " + e.getMessage();


        Problems problems = createProblemBuilder(status, detail);
        return handleExceptionInternal(e, problems,  new HttpHeaders(), status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Problems problems, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        Object body = new Object();

        if (problems == null) {
            Problems problemsBody = new Problems();

            problemsBody.setDetail(problems.getDetail());
            problemsBody.setStatus(problems.getStatus());
            problemsBody.setTimestamp(problems.getTimestamp());
            problemsBody.setUserMessage(problems.getUserMessage());

            body = problemsBody;
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problems createProblemBuilder(HttpStatus status, String detail) {

        Problems problems = new Problems();
                problems.setTimestamp(LocalDateTime.now());
                problems.setStatus(status.value());
                problems.setDetail(detail);

        return problems;
    }
}
