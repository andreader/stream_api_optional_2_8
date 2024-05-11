package pro.sky.stream_api_optional_2_8.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidNameException extends ResponseException {
    @Override
    public HttpStatus getStatus() {
        return super.getStatus();
    }

    public InvalidNameException(String message, HttpStatus status) {
        super(message, status);
    }
}
