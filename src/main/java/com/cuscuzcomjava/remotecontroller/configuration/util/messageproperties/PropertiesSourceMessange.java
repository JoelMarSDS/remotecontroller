package com.cuscuzcomjava.remotecontroller.configuration.util.messageproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class PropertiesSourceMessange {

    private static final ResourceBundle MSG_PROPERTIES = ResourceBundle.getBundle("messages");

    public static String getMessageSource(String message) {
        if (MSG_PROPERTIES.containsKey(message)) {
            return MSG_PROPERTIES.getString(message);
        }
        return "";
    }
}
