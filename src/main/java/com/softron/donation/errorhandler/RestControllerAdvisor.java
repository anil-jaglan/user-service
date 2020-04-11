package com.softron.donation.errorhandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.softron.donation.dto.ApiError;
import com.softron.donation.exceptions.BadRequestException;
import com.softron.donation.exceptions.NoRecordExistsException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestControllerAdvisor {

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<Object> handlePersistenceException(PersistenceException ex,
            final HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getMessage());
        this.logError(ex, request);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(NoRecordExistsException.class)
    public ResponseEntity<Object> handleNoRecordExistsException(NoRecordExistsException ex,
            final HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), ex.getMessage());
        this.logError(ex, request);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, final HttpServletRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     *
     * This exception is thrown when argument annotated with @Valid failed
     * validation.
     *
     * @param ex
     *            The exception.
     * @param response
     *            The response.
     * @return The response entity.
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> processValidationError(final MethodArgumentNotValidException ex,
            final HttpServletResponse response) {

        final BindingResult result = ex.getBindingResult();
        final Map<String, String> errorData = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            errorData.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.error("{} : {}", HttpStatus.BAD_REQUEST, errorData);
        return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            final HttpServletRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        log.error("Error: {}", ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,
            final HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
                    + violation.getMessage());
        }
        log.error("Error: {}", ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
            final HttpServletRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getSimpleName();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            final HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        logError(ex, request);
        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ NoHandlerFoundException.class })
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
            final HttpServletRequest request) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        logError(ex, request);
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, HttpServletRequest request) {
        logError(ex, request);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occurred", ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    protected void logError(Exception ex, HttpServletRequest request) {
        final String url = request.getRequestURL().toString();
        log.error("Exception in calling {}, Error: {}", url, ex.getMessage());
        log.error("Error statck: ", ex);
        if (log.isTraceEnabled()) {
            log.trace("Error statck: ", ex);
        }
    }
}
