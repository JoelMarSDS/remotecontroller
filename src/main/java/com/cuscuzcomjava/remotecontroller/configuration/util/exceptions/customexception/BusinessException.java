package com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception;

public class BusinessException extends RuntimeException {

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, Throwable messageCase){
        super(message, messageCase);
    }
}
