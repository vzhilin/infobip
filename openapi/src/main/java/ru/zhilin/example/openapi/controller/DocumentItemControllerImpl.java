package ru.zhilin.example.openapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import ru.zhilin.example.model.dao.DocumentDao;
import ru.zhilin.example.model.dao.DocumentItemDao;
import ru.zhilin.example.model.entity.DocumentEntity;
import ru.zhilin.example.model.entity.DocumentItemEntity;
import ru.zhilin.example.openapi.dto.DocumentItem;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/documents/{documentId}/items")
@Transactional
public class DocumentItemControllerImpl implements DocumentItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentItemControllerImpl.class);

    private final DocumentDao documentDao;

    private final DocumentItemDao documentItemDao;

    @Autowired
    public DocumentItemControllerImpl(DocumentDao documentDao, DocumentItemDao documentItemDao) {
        this.documentDao = documentDao;
        this.documentItemDao = documentItemDao;
    }

    private static DocumentItem of(DocumentItemEntity entity) {
        DocumentItem item = new DocumentItem();
        item.setId(entity.getId());
        item.setName(entity.getName());
        item.setPrice(entity.getPrice());
        return item;
    }

    @Override
    @GetMapping(path = "/{itemId}")
    public DocumentItem getDocumentItem(@PathVariable long documentId, @PathVariable long itemId) {
        DocumentEntity documentEntity = documentDao.getDocumentById(documentId);
        if (documentEntity == null) {
            throw new DocumentNotFoundException();
        }

        DocumentItemEntity documentItemEntity = documentItemDao.getDocumentItemById(documentEntity, itemId);
        if (documentItemEntity == null) {
            throw new DocumentItemNotFoundException();
        }
        return of(documentItemEntity);
    }

    @Override
    @GetMapping
    public List<DocumentItem> getDocumentItems(@PathVariable long documentId) {
        DocumentEntity documentEntity = documentDao.getDocumentById(documentId);
        if (documentEntity == null) {
            throw new DocumentNotFoundException();
        }

        List<DocumentItemEntity> documentItemEntities = documentItemDao.getDocumentItems(documentEntity);
        if (documentItemEntities == null) {
            return Collections.emptyList();
        }
        return documentItemEntities.stream().map(DocumentItemControllerImpl::of).collect(Collectors.toList());
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> addDocumentItem(@PathVariable long documentId, @RequestBody DocumentItem item) {
        DocumentEntity documentEntity = documentDao.getDocumentById(documentId);
        if (documentEntity == null) {
            throw new DocumentNotFoundException();
        }

        DocumentItemEntity documentItemEntity = new DocumentItemEntity();
        documentItemEntity.setName(item.getName());
        documentItemEntity.setPrice(item.getPrice());
        documentItemEntity.setDocument(documentEntity);
        documentItemDao.addDocumentItem(documentItemEntity);

        URI location = URI.create("/documents/" + documentId + "/items/" + documentItemEntity.getId());
        return ResponseEntity.created(location).build();
    }

    @Override
    @PutMapping(path = "/{itemId}")
    @ResponseStatus(NO_CONTENT)
    public void updateDocumentItem(@PathVariable long documentId, @PathVariable long itemId, @RequestBody DocumentItem item) {
        DocumentEntity documentEntity = documentDao.getDocumentById(documentId);
        if (documentEntity == null) {
            throw new DocumentNotFoundException();
        }

        DocumentItemEntity documentItemEntity = documentItemDao.getDocumentItemById(documentEntity, itemId);
        if (documentItemEntity == null) {
            throw new DocumentItemNotFoundException();
        }

        documentItemEntity.setName(item.getName());
        documentItemEntity.setPrice(item.getPrice());
        documentItemDao.updateDocumentItem(documentItemEntity);
    }

    @Override
    @DeleteMapping("/{itemId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteDocumentItem(@PathVariable long documentId, @PathVariable long itemId) {
        DocumentEntity documentEntity = documentDao.getDocumentById(documentId);
        if (documentEntity == null) {
            throw new DocumentNotFoundException();
        }

        DocumentItemEntity documentItemEntity = documentItemDao.getDocumentItemById(documentEntity, itemId);
        if (documentItemEntity == null) {
            throw new DocumentItemNotFoundException();
        }
        documentItemDao.deleteDocumentItem(documentItemEntity);
    }
}
