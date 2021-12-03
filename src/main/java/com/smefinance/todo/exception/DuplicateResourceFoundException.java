package com.smefinance.todo.exception;

import com.smefinance.todo.contants.ErrorCode;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class DuplicateResourceFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateResourceFoundException(Class<?> clazz, String... searchParamsMap) {
        super(DuplicateResourceFoundException.generateMessage(clazz.getSimpleName(),
                toMap(searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return "Duplicate " + StringUtils.capitalize(entity) + " found for parameters " + searchParams;
    }

    private static Map<String, String> toMap(String... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException(ErrorCode.EXCEPTION_RESOURCE_DUPLICATE_FOUND_INVALID_ENTRIES);
        return IntStream.range(0, entries.length / 2).map(i -> i * 2).collect(HashMap::new,
                (m, i) -> m.put(entries[i], entries[i + 1]), Map::putAll);
    }

}