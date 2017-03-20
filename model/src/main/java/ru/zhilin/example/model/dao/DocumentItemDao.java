package ru.zhilin.example.model.dao;

import java.util.List;

import ru.zhilin.example.model.entity.DocumentEntity;
import ru.zhilin.example.model.entity.DocumentItemEntity;

/**
 * Data access object (DAO) to deal with document items objects storage.
 */
public interface DocumentItemDao {
    DocumentItemEntity getDocumentItemById(DocumentEntity documentDto, long itemId);

    List<DocumentItemEntity> getDocumentItems(DocumentEntity documentDto);

    void addDocumentItem(DocumentItemEntity item);

    void updateDocumentItem(DocumentItemEntity item);

    void deleteDocumentItem(DocumentItemEntity item);
}
