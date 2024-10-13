package com.peche.app.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Handle AccessDeniedException. Triggered when an user try to access a resource that he does not have permission.
     *
     * @param ex      AccessDeniedException
     * @param request WebRequest
     * @return the errorMessage object
     */
    @ExceptionHandler({AccessDeniedException.class})
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage()
        );
        problemDetail.setInstance(URI.create(request.getRequestURL().toString())); //getDescription(false).replace("uri=","")
        problemDetail.setProperty("date", LocalDateTime.now());

        return problemDetail;
    }

    @ExceptionHandler(IllegalBodyException.class)
    public ProblemDetail handleIllegalBodyException(IllegalBodyException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage()
        );
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("date", LocalDateTime.now());
        problemDetail.setProperty("field", ex.getArg());
        return problemDetail;
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("date", LocalDateTime.now());
        problemDetail.setProperty("field", ex.getArg());
        return problemDetail;
    }

    @ExceptionHandler(DataSyncException.class)
    public ProblemDetail handleDataSyncException(DataSyncException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("date", LocalDateTime.now());
        problemDetail.setProperty("cause", ex.getCause());
        return problemDetail;
    }


}
