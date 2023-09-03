package com.SoftTech.PayPlanet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageProvider {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code){
        return this.messageSource.getMessage("E".concat(code), null, Locale.ENGLISH);
    }
}
