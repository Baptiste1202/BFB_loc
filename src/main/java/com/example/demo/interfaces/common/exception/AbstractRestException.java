package com.example.demo.interfaces.common.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;

public abstract class AbstractRestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5846250763816026669L;

    public abstract HttpStatus getHttpStatus();
    public String getType(){
        return this.getClass().getSimpleName();
    }
}

