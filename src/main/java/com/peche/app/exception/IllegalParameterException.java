package com.peche.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class IllegalParameterException extends RuntimeException implements Serializable {
    private final String arg;
    @Serial
    private static final long serialVersionUID = 1L;

    public IllegalParameterException(String message, String ...arg) {
        super(message.concat("<<< argument >>>").concat(Arrays.toString(arg)));
        this.arg = Arrays.toString(arg);
    }
}
