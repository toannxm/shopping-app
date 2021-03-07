package app.shopping.interceptor;

import app.shopping.entity.dto.ResponseError;
import app.shopping.rest.exception.ResourceAlreadyExistsException;
import app.shopping.rest.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@AllArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ResponseError responseError = new ResponseError(messageSource.getMessage("resource.exists", null, Locale.US), ex.getMessage());
        return handleExceptionInternal(ex, responseError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        ResponseError responseError = new ResponseError(messageSource.getMessage("resource.not_found", null, Locale.US), ex.getMessage());
        return handleExceptionInternal(ex, responseError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleUnExpectation(RuntimeException ex, WebRequest request) {
        ResponseError responseError = new ResponseError(messageSource.getMessage("internal_server_error", null, Locale.US), ex.getMessage());
        return handleExceptionInternal(ex, responseError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}