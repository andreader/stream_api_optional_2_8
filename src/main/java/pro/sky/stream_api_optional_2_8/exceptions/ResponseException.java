package pro.sky.stream_api_optional_2_8.exceptions;

import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {
    private final HttpStatus status;

    public ResponseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}