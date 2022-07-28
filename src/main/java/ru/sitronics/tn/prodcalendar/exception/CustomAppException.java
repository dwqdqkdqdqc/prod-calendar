package ru.sitronics.tn.prodcalendar.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class CustomAppException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final List<String> errors;

    public CustomAppException(String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message, null, null);
    }

    public CustomAppException(String message, List<String> errors) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message, errors, null);
    }

    /**
     * @param   throwable   for chain errors in response message and error log;
     */
    public CustomAppException(String message, Throwable throwable) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message, null, throwable);
    }

    /**
     * @param   errors   for chain errors in response message;
     * @param   throwable   for chain errors in error log;
     */
    public CustomAppException(String message, List<String> errors, Throwable throwable) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message, errors, throwable);
    }

    public CustomAppException(HttpStatus httpStatus, String message) {
        this(httpStatus, message, null, null);
    }

    public CustomAppException(HttpStatus httpStatus, List<String> errors) {
        this(httpStatus, null, errors, null);
    }

    /**
     * @param   throwable   for chain errors in response message and error log;
     */
    public CustomAppException(HttpStatus httpStatus, Throwable throwable) {
        this(httpStatus, null, null, throwable);
    }

    public CustomAppException(HttpStatus httpStatus, String message, List<String> errors) {
        this(httpStatus, message, errors, null);
    }

    /**
     * @param   throwable   for chain errors in response message and error log;
     */
    public CustomAppException(HttpStatus httpStatus, String message, Throwable throwable) {
        this(httpStatus, message, null, throwable);
    }

    /**
     * @param   errors   for chain errors in response message;
     * @param   throwable   for chain errors in error log;
     */
    public CustomAppException(HttpStatus httpStatus, String message, List<String> errors, Throwable throwable) {
        super(message, throwable);
        this.httpStatus = httpStatus;
        this.errors = errors;
    }
}
