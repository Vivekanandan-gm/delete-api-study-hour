package org.acme.controller;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.acme.exception.RequestValidationException;
import org.acme.model.Case;
import org.acme.repository.CaseRepository;
import org.jboss.resteasy.reactive.RestPath;

import java.util.Optional;

@Path("/api")
public class CaseController {
    
    @DELETE
    @Path("{case_id}")
    public Response deleteCaseById(@RestPath("case_id") String caseId) {
        if(caseId == null) {
            throw new RequestValidationException("case id is blank");
        }
        Optional<Case> caseD = CaseRepository.getCaseById(caseId);
        if(caseD.isEmpty()) {
            throw new NotFoundException("Case not available");
        }
        
        return Response.ok().build();
    }

    @GET
    @Path("/test")
    public Response test() {
        System.out.println("called");
        return Response.status(Response.Status.BAD_REQUEST).entity("test2").build();
    }
}
