package com.dawidantecki.watchers.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Messages {

    private MessageSource messageSource;

    @Autowired
    public Messages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private MessageSourceAccessor accessor;

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource);
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }
}
