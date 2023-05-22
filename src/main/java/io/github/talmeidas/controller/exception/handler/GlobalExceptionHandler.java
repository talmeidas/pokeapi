package io.github.talmeidas.controller.exception.handler;

import feign.FeignException;
import io.github.talmeidas.dto.HttpResponseStatusDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponseStatusDTO handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception, final Locale locale) {
        final List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        final String message = errors.stream().map(error -> messageSource.getMessage(error, locale)).collect(Collectors.joining(", "));
        return buildHttpResponseStatus(HttpStatus.BAD_REQUEST, message, webRequest.getContextPath());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public HttpResponseStatusDTO handleException(final Exception exception, final Locale locale) {
        log.error("Exception: {}", exception.getMessage());
        final String message = messageSource.getMessage("exception.unexpected", null, locale);
        return buildHttpResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR, message, webRequest.getContextPath());
    }

    public static HttpResponseStatusDTO buildHttpResponseStatus(final HttpStatus httpStatus, final String message, final String path) {
        return new HttpResponseStatusDTO(LocalDateTime.now(), httpStatus.value(), httpStatus.getReasonPhrase(), message, path);
    }

    @ResponseBody
    @ExceptionHandler(FeignException.class)
    public HttpResponseStatusDTO handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return buildHttpResponseStatus(HttpStatus.valueOf(response.getStatus()), e.getMessage(), webRequest.getContextPath());
    }
}