package pro.sky.stream_api_optional_2_8.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ArrayIsFullException extends ResponseException {
    public ArrayIsFullException(String message) {
        super(message);
    }

}
