package ru.zhilin.example.openapi.controller;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "Document item not found")
public class DocumentItemNotFoundException extends RuntimeException {
    public DocumentItemNotFoundException() {
    }

    public DocumentItemNotFoundException(String message) {
        super(message);
    }

    public DocumentItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentItemNotFoundException(Throwable cause) {
        super(cause);
    }

    public DocumentItemNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
