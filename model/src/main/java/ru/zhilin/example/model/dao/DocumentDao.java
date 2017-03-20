package ru.zhilin.example.model.dao;

import ru.zhilin.example.model.entity.DocumentEntity;

/**
 * Data access object (DAO) to deal with document objects storage.
 */
public interface DocumentDao {
    /**
     * Retrieves document from storage.
     *
     * @param id document identifier
     * @return requested document
     */
    DocumentEntity getDocumentById(long id);

    /**
     * Adds document to storage.
     *
     * @param document document to add
     */
    void addDocument(DocumentEntity document);

    /**
     * Updates document in storage.
     *
     * @param document document to update
     */
    void updateDocument(DocumentEntity document);

    /**
     * Deletes specified document from storage.
     *
     * @param document document to delete
     */
    void deleteDocument(DocumentEntity document);
}
