package com.cuscuzcomjava.remotecontroller.configuration.util.messageproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class PropertiesSourceMessange {

    @Autowired
    private static MessageSource messageSource;

    public static String getMessageSource(String message) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(message, null, locale);
    }

}
