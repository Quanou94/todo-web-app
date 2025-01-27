package ch.cern.todo;

import ch.cern.todo.exception.CategoryNotFoundException;
import ch.cern.todo.exception.ErrorResponse;
import ch.cern.todo.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

public class ApplicationExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CategoryNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
