package org.acme.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class CustomExceptionHandler implements ExceptionMapper<CustomException> {
    @Override
    public Response toResponse(CustomException exception) {
        if (exception instanceof RequestValidationException) {
            // Construct response object
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (exception instanceof BusinessValidationException) {
            // Construct response object
            return Response.status(422).build();
        }
        if (exception instanceof AuthException) {
            // Construct response object
            return Response.status(403).build();
        }
        return null;
    }
}
