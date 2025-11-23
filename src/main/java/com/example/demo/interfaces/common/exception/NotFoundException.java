package com.example.demo.interfaces.common.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class NotFoundException extends AbstractRestException {

    @Serial
    private static final long serialVersionUID = 4833441532411808448L;
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    private final String message;
}

