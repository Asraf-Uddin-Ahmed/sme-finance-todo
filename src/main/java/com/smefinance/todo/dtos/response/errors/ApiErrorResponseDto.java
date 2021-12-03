package com.smefinance.todo.dtos.response.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString(callSuper = true)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
@JsonInclude(Include.NON_EMPTY)
public class ApiErrorResponseDto {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private String errorCode;
    private String message;
    private String debugMessage;
    private List<ApiSubErrorResponseDto> subErrors;

    public ApiErrorResponseDto() {
        this.subErrors = new ArrayList<>();
        this.timestamp = new Date();
    }

    public void addValidationError(ApiSubErrorResponseDto apiSubErrorResponseDto) {
        this.subErrors.add(apiSubErrorResponseDto);
    }

}