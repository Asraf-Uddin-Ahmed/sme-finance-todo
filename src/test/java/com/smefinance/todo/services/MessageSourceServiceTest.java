package com.smefinance.todo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.smefinance.todo.config.CustomLocaleResolver;
import com.smefinance.todo.services.imp.MessageSourceServiceImpl;

@SpringBootTest
class MessageSourceServiceTest {

	@InjectMocks
    private MessageSourceServiceImpl messageSourceService;

	@Mock
	private MessageSource messageSource;
	@Mock
	private CustomLocaleResolver customLocaleResolver;
	
	@Test
	public void whenPropertyProvided_thenGetReleventMessage() {
		String givenProperty = "prop.key";
		String givenMessage = "foo bar";
		when(messageSource.getMessage(givenProperty, null, LocaleContextHolder.getLocale())).thenReturn(givenMessage);
        
		String foundMessage = messageSourceService.getMessage(givenProperty);
		
		assertThat(foundMessage).isEqualTo(givenMessage);
	}

	@Test
	public void whenPropertyAndHttpServletRequestProvided_thenGetReleventMessage() {
		String givenProperty = "prop.key";
		String givenMessage = "foo bar";
		when(messageSource.getMessage(givenProperty, null, customLocaleResolver.resolveLocale(any()))).thenReturn(givenMessage);
        
		String foundMessage = messageSourceService.getMessage(givenProperty, null);
		
		assertThat(foundMessage).isEqualTo(givenMessage);
	}

}
