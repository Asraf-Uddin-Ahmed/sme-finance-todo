package com.smefinance.todo.contants;


public class ErrorCode {

    public static final String HTTP_ERROR_MISSING_SERVLET_REQUEST_PARAMETER = "http-error.missing-servlet-request-parameter";
    public static final String HTTP_ERROR_MESSAGE_NOT_READABLE = "http-error.message.not-readable";
    public static final String HTTP_ERROR_MESSAGE_NOT_WRITABLE = "http-error.message.not-writable";
    public static final String EXCEPTION = "exception";
    public static final String EXCEPTION_RESOURCE_DUPLICATE_FOUND = "exception.resource.duplicate-found";
    public static final String EXCEPTION_RESOURCE_DUPLICATE_FOUND_INVALID_ENTRIES = "exception.resource.duplicate-found.invalid.entries";
    public static final String EXCEPTION_RESOURCE_NOT_FOUND = "exception.resource.not-found";
    public static final String EXCEPTION_DATA_INTEGRITY_VIOLATION = "exception.data-integrity-violation";
    public static final String EXCEPTION_DATA_INTEGRITY_VIOLATION_CONSTRAINT_VIOLATION = "exception.data-integrity-violation.constraint-violation";
    public static final String EXCEPTION_METHOD_ARGUMENT_TYPE_MISMATCH = "exception.method-argument-type-mismatch";
    public static final String EXCEPTION_ILLEGAL_ARGUMENT = "exception.illegal-argument";
    public static final String VALIDATION_ERROR = "validation.error";

    private ErrorCode() {
    }

}
