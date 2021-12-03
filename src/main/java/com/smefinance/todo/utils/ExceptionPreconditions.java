package com.smefinance.todo.utils;

import com.smefinance.todo.exception.ResourceNotFoundException;

public final class ExceptionPreconditions {

    private ExceptionPreconditions() {
        throw new AssertionError();
    }

    public static <T> T entityNotFound(final Class<T> clazz, String... searchParamsMap) {
        throw new ResourceNotFoundException(clazz, searchParamsMap);
    }
}