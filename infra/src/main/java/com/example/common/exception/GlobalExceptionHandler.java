package com.example.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Pattern SPLIT_PATTERN = Pattern.compile(",");

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.error("Bad request! Message : {}", ex.getBody().getDetail());
        return ResponseEntity.badRequest().body(ex.getBody());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ProblemDetail> handleBusinessException(BusinessException ex, Locale locale) {
        return createErrorResponseFromMessageSource(ex.getKey(), locale, ex.getArgs());
    }

    private ResponseEntity<ProblemDetail> createErrorResponseFromMessageSource(String key, Locale locale, String... args) {
        List<String> messages = retrieveLocalizationMessage(key, locale, args);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, messages.get(1));
        problemDetail.setTitle(messages.get(0));
        return ResponseEntity.badRequest().body(problemDetail);
    }

    private List<String> retrieveLocalizationMessage(String key, Locale locale, String... args) {
        String message = messageSource.getMessage(key, args, locale);
        return SPLIT_PATTERN.splitAsStream(message).toList();
    }

}
