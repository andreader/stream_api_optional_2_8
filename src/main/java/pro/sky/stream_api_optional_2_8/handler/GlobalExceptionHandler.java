package pro.sky.stream_api_optional_2_8.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pro.sky.stream_api_optional_2_8.exceptions.ArrayIsFullException;
import pro.sky.stream_api_optional_2_8.exceptions.EmployeeAlreadyAddedException;
import pro.sky.stream_api_optional_2_8.exceptions.EmployeeNotFoundException;
import pro.sky.stream_api_optional_2_8.model.ErrorDTO;
import pro.sky.stream_api_optional_2_8.model.ResponseException;

import java.util.Date;
import java.util.function.Function;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorDTO> handleException(Exception ex, Function<Exception, ResponseEntity<ErrorDTO>> responseFunction) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getMessage());
        errorDTO.setStatus(String.valueOf(((ResponseException) ex).getStatus().value()));
        errorDTO.setTime(new Date().toString());

        return responseFunction.apply(ex);
    }

    private Function<Exception, ResponseEntity<ErrorDTO>> generateResponseFunction() {
        return e -> {
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setMessage(e.getMessage());
            errorDTO.setStatus(String.valueOf(((ResponseException) e).getStatus().value()));
            errorDTO.setTime(new Date().toString());
            return ResponseEntity.status(((ResponseException) e).getStatus()).body(errorDTO);
        };
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return handleException(ex, generateResponseFunction());
    }

    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public ResponseEntity<ErrorDTO> handleEmployeeAlreadyAddedException(EmployeeAlreadyAddedException ex) {
        return handleException(ex, generateResponseFunction());
    }

    @ExceptionHandler(ArrayIsFullException.class)
    public ResponseEntity<ErrorDTO> handleArrayIsFullException(ArrayIsFullException ex) {
        return handleException(ex, generateResponseFunction());
    }
}