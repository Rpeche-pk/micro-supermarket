package com.peche.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class NotFoundException extends RuntimeException implements Serializable {
    private final String arg;
    @Serial
    private static final long serialVersionUID = 1L;
    public NotFoundException(String message, String arg) {
        super(message.concat("<<< argument >>>").concat(arg));
        this.arg = arg;
    }
}
