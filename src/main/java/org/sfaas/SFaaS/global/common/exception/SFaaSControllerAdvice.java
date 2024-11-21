package org.sfaas.SFaaS.global.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.sfaas.SFaaS.global.common.response.ErrorType;
import org.sfaas.SFaaS.global.common.response.SFaaSResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class SFaaSControllerAdvice {

    /**
     * 400 BAD_REQUEST
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SFaaSResponse<?>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {

        Errors errors = e.getBindingResult();
        Map<String, String> validateDetails = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validateDetails.put(validKeyName, error.getDefaultMessage());
        }
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(SFaaSResponse.error(ErrorType.REQUEST_VALIDATION, validateDetails), e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<SFaaSResponse<?>> handlerMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(SFaaSResponse.error(ErrorType.INVALID_TYPE), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<SFaaSResponse<?>> handlerMissingRequestHeaderException(final MissingRequestHeaderException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(SFaaSResponse.error(ErrorType.INVALID_MISSING_HEADER), e.getStatusCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<SFaaSResponse<?>> handlerHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(SFaaSResponse.error(ErrorType.INVALID_HTTP_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<SFaaSResponse<?>> handlerHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(SFaaSResponse.error(ErrorType.METHOD_NOT_ALLOWED), e.getStatusCode());
    }

    /**
     * 500 INTERNEL_SERVER
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<SFaaSResponse<?>> handleException(final Exception e, final HttpServletRequest request) throws IOException {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(SFaaSResponse.error(ErrorType.INTERNAL_SERVER), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<SFaaSResponse<?>> handlerIllegalArgumentException(final IllegalArgumentException e, final HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(SFaaSResponse.error(ErrorType.INTERNAL_SERVER), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<SFaaSResponse<?>> handlerIOException(final IOException e, final HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(SFaaSResponse.error(ErrorType.INTERNAL_SERVER), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<SFaaSResponse<?>> handlerRuntimeException(final RuntimeException e, final HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(SFaaSResponse.error(ErrorType.INTERNAL_SERVER), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * CUSTOM_ERROR
     */
    @ExceptionHandler(SFaaSException.class)
    public ResponseEntity<SFaaSResponse<?>> handleCustomException(SFaaSException e) {
        return new ResponseEntity<>(SFaaSResponse.error(e.getErrorType()), HttpStatusCode.valueOf(e.getHttpStatus()));
    }

}
