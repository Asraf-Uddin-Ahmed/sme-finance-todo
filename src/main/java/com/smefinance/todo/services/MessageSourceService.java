package com.smefinance.todo.services;

import javax.servlet.http.HttpServletRequest;

public interface MessageSourceService {
	String getMessage(String propertyKey);
	String getMessage(String propertyKey, HttpServletRequest httpServletRequest);
}
