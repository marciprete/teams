package it.maconsulting.teams.presentation.error;

import it.maconsulting.microkernel.exceptions.DomainException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class uses AOP to intercept errors in the controllers.
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * error handler for the {@link DomainException}
     * @param ex the DomainException
     * @return a response entity with the error
     */
    @ExceptionHandler({DomainException.class/*, HttpMessageNotReadableException.class*/})
    public ResponseEntity<Object> domainModelNotFoundException(Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = Arrays.asList(ex.getMessage());
        return new ResponseEntity<>(buildBody(errors, status.value()), status);
    }

    /**
     * error handle for @Valid
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());


        return new ResponseEntity<>(buildBody(errors, status.value()), headers, status);

    }

    private Map<String, Object> buildBody(List<String> errors, int status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status);

        body.put("errors", errors);
        return body;
    }

}
