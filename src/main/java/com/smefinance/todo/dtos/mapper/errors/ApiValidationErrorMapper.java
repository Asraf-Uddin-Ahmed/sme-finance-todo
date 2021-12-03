package com.smefinance.todo.dtos.mapper.errors;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.smefinance.todo.dtos.response.errors.ApiValidationErrorResponseDto;

public interface ApiValidationErrorMapper {

    List<ApiValidationErrorResponseDto> getApiValidationErrors(Set<ConstraintViolation<?>> constraintViolations);

    ApiValidationErrorResponseDto getApiValidationError(FieldError fieldError);

    ApiValidationErrorResponseDto getApiValidationError(ObjectError objectError);

}