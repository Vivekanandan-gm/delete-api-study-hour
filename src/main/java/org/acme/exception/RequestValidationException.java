package org.acme.exception;

public class RequestValidationException extends ValidationException {
    public RequestValidationException(String message) {
        super(message);
    }
}
