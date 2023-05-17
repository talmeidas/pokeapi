package io.github.talmeidas.controller.exception.handler;

import io.github.talmeidas.dto.*;
import lombok.extern.log4j.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    private final WebRequest webRequest;

    public GlobalExceptionHandler(MessageSource messageSource, WebRequest webRequest) {
        this.messageSource = messageSource;
        this.webRequest = webRequest;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public HttpResponseStatusDTO handleHttpMessageNotReadableException(final HttpMessageNotReadableException exception,
            final Locale locale) {
        final String message = messageSource.getMessage("exception.request.body.is.invalid.or.missing", null, locale);
        return buildHttpResponseStatus(HttpStatus.BAD_REQUEST, message, webRequest.getContextPath());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public HttpResponseStatusDTO handleBindException(final BindException exception, final Locale locale) {
        final Object[] args = { StringUtils.substringBetween(exception.getMessage(), "property '", "'") };
        final String message = messageSource.getMessage("exception.field.format", args, locale);
        return buildHttpResponseStatus(HttpStatus.BAD_REQUEST, message, webRequest.getContextPath());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponseStatusDTO handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception,
            final Locale locale) {
        final List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        final String message = errors.stream().map(error -> messageSource.getMessage(error, locale))
                .collect(Collectors.joining(", "));
        return buildHttpResponseStatus(HttpStatus.BAD_REQUEST, message, webRequest.getContextPath());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public HttpResponseStatusDTO handleConstraintViolationException(final ConstraintViolationException exception,
            final Locale locale) {
        final String message = messageSource.getMessage(exception.getMessage(), null, locale);
        return buildHttpResponseStatus(HttpStatus.BAD_REQUEST, message, webRequest.getContextPath());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public HttpResponseStatusDTO handleIllegalArgumentException(final IllegalArgumentException exception,
            final Locale locale) {
        final String message = messageSource.getMessage(exception.getMessage(), null, locale);
        return buildHttpResponseStatus(HttpStatus.BAD_REQUEST, message, webRequest.getContextPath());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public HttpResponseStatusDTO handleDataIntegrityViolationException(final DataIntegrityViolationException exception,
            final Locale locale) {
        final String message = messageSource.getMessage("exception.unexpected.data.integrity.violation", null, locale);
        return buildHttpResponseStatus(HttpStatus.CONFLICT, message, webRequest.getContextPath());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(EntityNotFoundException.class)
    public HttpResponseStatusDTO handleEntityNotFoundException(final EntityNotFoundException exception,
            final Locale locale) {
        final String message = messageSource.getMessage(exception.getMessage(), null, locale);
        return buildHttpResponseStatus(HttpStatus.NOT_FOUND, message, webRequest.getContextPath());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public HttpResponseStatusDTO handleAccessDeniedException(final AccessDeniedException exception,
            final Locale locale) {
        final String message = messageSource.getMessage("exception.access.denied", null, locale);
        return buildHttpResponseStatus(HttpStatus.FORBIDDEN, message, webRequest.getContextPath());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public HttpResponseStatusDTO handleException(final Exception exception, final Locale locale) {
        log.error("Exception: {}", exception.getMessage());
        final String message = messageSource.getMessage("exception.unexpected", null, locale);
        return buildHttpResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR, message, webRequest.getContextPath());
    }

    public static HttpResponseStatusDTO buildHttpResponseStatus(final HttpStatus httpStatus, final String message,
            final String path) {
        return new HttpResponseStatusDTO(LocalDateTime.now(), httpStatus.value(), httpStatus.getReasonPhrase(), message,
                path);
    }
}