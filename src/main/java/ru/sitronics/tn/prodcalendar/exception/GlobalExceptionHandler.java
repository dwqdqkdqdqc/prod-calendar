package ru.sitronics.tn.prodcalendar.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Sources:
 * @see <a href="https://gist.githubusercontent.com/ehabqadah/30e17bebe0b7b00e40c6565868d0ec37/raw/20874b4a8f7861072f6b2aff7daa4b8b1c8b995f/GeneralExceptionHandler.java">sources 1</a>
 * @see <a href="https://smattme.com/blog/technology/spring-boot-exception-handling">sources 2</a>
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String FIELD_ERROR_SEPARATOR = ": ";

    private static final String ERRORS_FOR_PATH = "errors {} for path {}";
    private static final String PATH = "path";
    private static final String ERRORS = "error";
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String TIMESTAMP = "timestamp";
    private static final String TYPE = "type";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + FIELD_ERROR_SEPARATOR + error.getDefaultMessage())
                .collect(Collectors.toList());
        return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = exception.getLocalizedMessage();
        return getExceptionResponseEntity(exception, status, request, Collections.singletonList(error.substring(0, error.indexOf(FIELD_ERROR_SEPARATOR))));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception, WebRequest request) {
        final List<String> validationErrors = exception.getConstraintViolations().stream().
                map(violation -> violation.getPropertyPath() + FIELD_ERROR_SEPARATOR + violation.getMessage())
                .collect(Collectors.toList());
        return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
    }

    @ExceptionHandler(CustomAppException.class)
    public ResponseEntity<?> handleCustomErrors(CustomAppException e, WebRequest request) {
        final HttpStatus status = e.getHttpStatus();
        final List<String> errors = (e.getErrors() == null || e.getErrors().isEmpty()) ? getExceptionMessageChain(e) : e.getErrors();
        return getExceptionResponseEntity(e, status, request, errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllErrors(Exception e, WebRequest request) {
        String error = e.getLocalizedMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (error.contains("No") || error.contains("Not") || error.contains("Found") || error.contains("Path"))
            status = HttpStatus.BAD_REQUEST;
        List<String> errors = null;
        if (error.contains(FIELD_ERROR_SEPARATOR))
            errors = error.split(FIELD_ERROR_SEPARATOR).length > 1 ? getExceptionMessageChain(e) : Collections.singletonList(error.substring(0, error.indexOf(FIELD_ERROR_SEPARATOR)));
        return getExceptionResponseEntity(e, status, request, errors);
    }

    private ResponseEntity<Object> getExceptionResponseEntity(Exception e, HttpStatus status, WebRequest request, List<String> errors) {
        final String path = request.getDescription(false);
        final Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, Instant.now());
        body.put(STATUS, status.value());
        body.put(MESSAGE, e.getLocalizedMessage() != null && e.getLocalizedMessage().split(".").length < 3 && errors != null && !e.getLocalizedMessage().equals(errors.get(0)) ? e.getLocalizedMessage() : getMessageForStatus(status));
        if (errors != null)
            body.put(ERRORS, errors);
        if (log.isErrorEnabled())
            body.put(TYPE, e.getClass().getSimpleName());
        body.put(PATH, path.startsWith("uri=") ? path.substring(4) : path);
        log.error(ERRORS_FOR_PATH, e, path);
        return new ResponseEntity<>(body, status);
    }

    private String getMessageForStatus(HttpStatus status) {
        return switch (status) {
            case NOT_FOUND -> "The resource does not exist";
            case INTERNAL_SERVER_ERROR -> "Something went wrong internally";
            case TOO_MANY_REQUESTS -> "Too many requests";
            case FORBIDDEN -> "Permission denied";
            case UNAUTHORIZED -> "Access denied!";
            case BAD_REQUEST -> "Invalid request";
            default -> status.getReasonPhrase();
        };
    }

    private static List<String> getExceptionMessageChain(Throwable throwable) {
        List<String> result = new ArrayList<>();
        while (throwable != null) {
            result.add(throwable.getMessage());
            throwable = throwable.getCause();
        }
        return result;
    }
}
