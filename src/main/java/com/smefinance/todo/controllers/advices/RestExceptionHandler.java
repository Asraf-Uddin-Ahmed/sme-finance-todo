package com.smefinance.todo.controllers.advices;

import com.smefinance.todo.contants.ErrorCode;
import com.smefinance.todo.dtos.mapper.errors.ApiErrorMapper;
import com.smefinance.todo.dtos.response.errors.ApiErrorResponseDto;
import com.smefinance.todo.exception.DuplicateResourceFoundException;
import com.smefinance.todo.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Log4j
class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final ApiErrorMapper apiErrorMapper;

    @Autowired
    private RestExceptionHandler(ApiErrorMapper apiErrorMapper) {
        this.apiErrorMapper = apiErrorMapper;
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getClass().getSimpleName() + " - ", ex);
        return buildResponseEntity(this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.BAD_REQUEST)
                .setMessageByErrorCode(ErrorCode.HttpError.MissingServletRequestParameter, ex.getParameterName())
                .setDebugMessage(ex).build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getClass().getSimpleName() + " - ", ex);
        return buildResponseEntity(this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .setMessageByErrorCode(ErrorCode.HttpError.MissingServletRequestParameter, ex.getContentType(),
                        ex.getSupportedMediaTypes().toString())
                .setDebugMessage(ex).build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getClass().getSimpleName() + " - ", ex);
        ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().initDefaultValidationError()
                .addValidationFieldErrors(ex.getBindingResult().getFieldErrors())
                .addValidationObjectErrors(ex.getBindingResult().getGlobalErrors()).build();
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        HttpMethod httpMethod = servletWebRequest.getHttpMethod();
        log.info(String.format("%s to %s", httpMethod == null ? null : httpMethod.toString(),
                servletWebRequest.getRequest().getServletPath()));
        log.error(ex.getClass().getSimpleName() + " - ", ex);
        return buildResponseEntity(this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.BAD_REQUEST)
                .setDebugMessage(ex).setMessageByErrorCode(ErrorCode.HttpError.Message.NOT_READABLE).build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getClass().getSimpleName() + " - ", ex);
        return buildResponseEntity(this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setDebugMessage(ex).setMessageByErrorCode(ErrorCode.HttpError.Message.NOT_WRITABLE).build());
    }


    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        log.error(ex.getClass().getSimpleName() + " - ", ex);
        ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().initDefaultValidationError()
                .addValidationErrors(ex.getConstraintViolations()).build();
        return buildResponseEntity(apiError);
    }


    @ExceptionHandler(DuplicateResourceFoundException.class)
    protected ResponseEntity<Object> handleDuplicateResourceFoundException(DuplicateResourceFoundException ex) {
        log.error(ex.getClass().getSimpleName() + " - ", ex);
        ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.CONFLICT)
                .setMessageByErrorCode(ErrorCode.Exception.Resource.DuplicateFound.VALUE).setDebugMessage(ex).build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getClass().getSimpleName() + " - ", ex);
        ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.NOT_FOUND)
                .setMessageByErrorCode(ErrorCode.Exception.Resource.NotFound.VALUE).setDebugMessage(ex).build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        log.error(ex.getClass().getSimpleName() + " - ", ex);
        ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.NOT_FOUND)
                .setMessageByErrorCode(ErrorCode.Exception.Resource.NotFound.VALUE).setDebugMessage(ex).build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElement(NoSuchElementException ex) {
        log.error(ex.getClass().getSimpleName() + " - ", ex);
        ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.NOT_FOUND)
                .setMessageByErrorCode(ErrorCode.Exception.Resource.NotFound.VALUE).setDebugMessage(ex).build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        log.error(ex.getClass().getSimpleName() + " : " + request.getContextPath() + " - ", ex);
        if (ex.getCause() instanceof ConstraintViolationException) {
            ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.BAD_REQUEST)
                    .setMessageByErrorCode(ErrorCode.Exception.DataIntegrityViolation.CONSTRAINT_VIOLATION)
                    .setDebugMessage(ex.getCause()).build();
            return buildResponseEntity(apiError);
        }
        if (ex.getCause() instanceof EntityExistsException) {
            ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.CONFLICT)
                    .setMessageByErrorCode(ErrorCode.Exception.Resource.DuplicateFound.VALUE)
                    .setDebugMessage(ex.getCause()).build();
            return buildResponseEntity(apiError);
        }
        ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setMessageByErrorCode(ErrorCode.Exception.DataIntegrityViolation.VALUE).setDebugMessage(ex).build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        log.error(ex.getClass().getSimpleName() + " : " + request.getContextPath() + " - ", ex);
        Class<?> clazz = ex.getRequiredType();
        ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.BAD_REQUEST)
                .setMessageByErrorCode(ErrorCode.Exception.MethodArgumentTypeMismatch.VALUE, ex.getName(),
                        ex.getValue(), clazz == null ? null : clazz.getSimpleName())
                .setDebugMessage(ex).build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        log.error(ex.getClass().getSimpleName() + " : " + request.getContextPath() + " - ", ex);
        ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.BAD_REQUEST)
                .setDebugMessage(ex).build();
        this.apiErrorMapper.setMessageByErrorCode(ErrorCode.Exception.IllegalArgument.VALUE);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        log.error(ex.getClass().getSimpleName() + " : " + request.getContextPath() + " - ", ex);
        ApiErrorResponseDto apiError = this.apiErrorMapper.initResponseDto().setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setMessageByErrorCode(ErrorCode.Exception.VALUE, ex.getClass().getSimpleName()).setDebugMessage(ex)
                .build();
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponseDto apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}