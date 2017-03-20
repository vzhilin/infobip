package ru.zhilin.example.openapi.controller;

import org.springframework.http.ResponseEntity;

import ru.zhilin.example.openapi.dto.Document;

public interface DocumentController {
    /**
     * Retrieves document.
     *
     * @param id document identifier
     * @return requested document
     */
    Document getDocument(long id);

    /**
     * Adds document.
     *
     * @param document document to add
     */
    ResponseEntity<Void> addDocument(Document document);

    /**
     * Updates document.
     *
     * @param id document identifier
     * @param document document
     */
    void updateDocument(long id, Document document);

    /**
     * Deletes specified document.
     *
     * @param id document identifier
     */
    void deleteDocument(long id);
}
