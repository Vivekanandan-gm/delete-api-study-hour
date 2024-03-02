package org.acme.exception;

public class BusinessValidationException extends ValidationException {
    public BusinessValidationException(String message) {
        super(message);
    }
}
