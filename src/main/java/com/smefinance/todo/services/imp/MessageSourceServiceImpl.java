package com.smefinance.todo.services.imp;

import com.smefinance.todo.services.MessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceServiceImpl implements MessageSourceService {

    private final MessageSource messageSource;

    @Autowired
    public MessageSourceServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String propertyKey) {
        return messageSource.getMessage(propertyKey, null, LocaleContextHolder.getLocale());
    }

}
