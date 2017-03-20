package ru.zhilin.example.openapi.controller;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "Document not found")
public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException() {
    }

    public DocumentNotFoundException(String message) {
        super(message);
    }

    public DocumentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentNotFoundException(Throwable cause) {
        super(cause);
    }

    public DocumentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
