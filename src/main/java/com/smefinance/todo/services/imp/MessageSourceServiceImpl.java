package com.smefinance.todo.services.imp;

import com.smefinance.todo.config.CustomLocaleResolver;
import com.smefinance.todo.services.MessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MessageSourceServiceImpl implements MessageSourceService {

    private final MessageSource messageSource;
    private final CustomLocaleResolver customLocaleResolver;

    @Autowired
    public MessageSourceServiceImpl(MessageSource messageSource, CustomLocaleResolver customLocaleResolver) {
        this.messageSource = messageSource;
        this.customLocaleResolver = customLocaleResolver;
    }

    public String getMessage(String propertyKey) {
        return messageSource.getMessage(propertyKey, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String propertyKey, HttpServletRequest httpServletRequest) {
        return messageSource.getMessage(propertyKey, null, customLocaleResolver.resolveLocale(httpServletRequest));
    }

}
