package com.smefinance.todo.dtos.mapper.errors.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.smefinance.todo.dtos.mapper.errors.ApiValidationErrorMapper;
import com.smefinance.todo.dtos.response.errors.ApiValidationErrorResponseDto;
import com.smefinance.todo.services.MessageSourceService;

@Component
public class ApiValidationErrorMapperImpl implements ApiValidationErrorMapper {

    private final MessageSourceService messageSourceService;

    @Autowired
    protected ApiValidationErrorMapperImpl(MessageSourceService messageSourceService) {
        this.messageSourceService = messageSourceService;
    }

    private ApiValidationErrorResponseDto getApiValidationError(ConstraintViolation<?> constraintViolation) {
        return new ApiValidationErrorResponseDto(constraintViolation.getRootBeanClass().getSimpleName(),
                ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().asString(),
                constraintViolation.getInvalidValue(), constraintViolation.getMessage(),
                messageSourceService.getMessage(constraintViolation.getMessage()));
    }

    public List<ApiValidationErrorResponseDto> getApiValidationErrors(
            Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream().map(this::getApiValidationError).collect(Collectors.toList());
    }

    public ApiValidationErrorResponseDto getApiValidationError(FieldError fieldError) {
        return new ApiValidationErrorResponseDto(fieldError.getObjectName(), fieldError.getField(),
                fieldError.getRejectedValue(), fieldError.getCode(),
                messageSourceService.getMessage(fieldError.getCode()));
    }

    public ApiValidationErrorResponseDto getApiValidationError(ObjectError objectError) {
        return new ApiValidationErrorResponseDto(objectError.getObjectName(), objectError.getCode(),
                messageSourceService.getMessage(objectError.getCode()));
    }

}
