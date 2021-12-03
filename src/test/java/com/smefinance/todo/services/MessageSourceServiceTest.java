package com.smefinance.todo.services;

import com.smefinance.todo.services.imp.MessageSourceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class MessageSourceServiceTest {

    @InjectMocks
    private MessageSourceServiceImpl messageSourceService;

    @Mock
    private MessageSource messageSource;

    @Test
    void whenPropertyProvided_thenGetRelevantMessage() {
        String givenProperty = "prop.key";
        String givenMessage = "foo bar";
        when(messageSource.getMessage(givenProperty, null, LocaleContextHolder.getLocale())).thenReturn(givenMessage);

        String foundMessage = messageSourceService.getMessage(givenProperty);

        assertThat(foundMessage).isEqualTo(givenMessage);
    }

}
