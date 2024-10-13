package com.peche.app.exception;

import java.io.Serial;
import java.io.Serializable;

public class DataSyncException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public DataSyncException(String message, Throwable cause) {
        super(message, cause);
    }
}
