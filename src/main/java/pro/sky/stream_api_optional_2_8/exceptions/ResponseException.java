package pro.sky.stream_api_optional_2_8.exceptions;

import org.springframework.http.HttpStatus;


public class ResponseException extends RuntimeException {
    private HttpStatus status;

    public ResponseException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return status;
    }
}