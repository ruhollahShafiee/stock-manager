package com.eshop.stockmanager.controller.exception;


import com.eshop.stockmanager.config.AppConfig;
import com.eshop.stockmanager.controller.response.ErrorTemplate;
import com.eshop.stockmanager.controller.response.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Collections;
import java.util.List;


@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String NOT_FOUND = "not found";
    public static final String METHOD_NOT_ALLOWED = "method not allowed";
    public static final String NOT_ACCEPTABLE = "not acceptable";
    public static final String INTERNAL_SERVER_ERROR = "internal server error";
    public static final String INVALID_REQUEST = "invalid request";
    public static final String ERROR_MESSAGE_TEMPLATE = "message: %s %n requested uri: %s";


    @Autowired
    AppConfig appConfig;


    @Override
    protected ResponseEntity handleNoHandlerFoundException(
            NoHandlerFoundException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getExceptionResponseEntity(exception, HttpStatus.NOT_FOUND, request, null, appConfig.getServerThrowErrorDetails());
    }

    @Override
    protected ResponseEntity handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        return getExceptionResponseEntity(exception, status, request,
                Collections.singletonList(exception.getLocalizedMessage()), appConfig.getServerThrowErrorDetails());
    }

    @ExceptionHandler({MoreThanTheStockException.class})
    public ResponseEntity handleMoreThanTheStockException(MoreThanTheStockException exception, WebRequest request) {
        ResponseStatus responseStatus =
                exception.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status = HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS;
        final String localizedMessage = exception.getLocalizedMessage();
        String message = (!StringUtils.isEmpty(localizedMessage) ? localizedMessage : status.getReasonPhrase());
        return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message), appConfig.getServerThrowErrorDetails());
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity handleAllExceptions(Exception exception, WebRequest request) {
        ResponseStatus responseStatus =
                exception.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        final String localizedMessage = exception.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = (!StringUtils.isEmpty(localizedMessage) ? localizedMessage : status.getReasonPhrase());
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path), exception);
        return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message), appConfig.getServerThrowErrorDetails());
    }

    private ResponseEntity getExceptionResponseEntity(final Exception exception,
                                                      final HttpStatus status,
                                                      final WebRequest request,
                                                      final List<String> errors, Boolean showErrorDetails) {
        ErrorTemplate error = new ErrorTemplate(errors, status.value(), getMessageForStatus(status), showErrorDetails);
        return new ResponseTemplate(Instant.now(), false, status, error, null).build();
    }

    private String getMessageForStatus(HttpStatus status) {
        switch (status) {
            case NOT_FOUND:
                return NOT_FOUND;
            case METHOD_NOT_ALLOWED:
                return METHOD_NOT_ALLOWED;
            case NOT_ACCEPTABLE:
                return NOT_ACCEPTABLE;
            case INTERNAL_SERVER_ERROR:
                return INTERNAL_SERVER_ERROR;
            case BAD_REQUEST:
                return INVALID_REQUEST;
            default:
                return status.getReasonPhrase();
        }
    }


}
