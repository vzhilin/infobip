package ru.zhilin.example.openapi.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

import ru.zhilin.example.openapi.dto.DocumentItem;

public interface DocumentItemController {
    DocumentItem getDocumentItem(long documentId, long itemId);

    List<DocumentItem> getDocumentItems(long documentId);

    ResponseEntity<Void> addDocumentItem(long documentId, DocumentItem item);

    void updateDocumentItem(long documentId, long itemId, DocumentItem item);

    void deleteDocumentItem(long documentId, long itemId);
}
