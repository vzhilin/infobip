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

import ru.zhilin.example.model.dao.DocumentDao;
import ru.zhilin.example.model.entity.DocumentEntity;
import ru.zhilin.example.openapi.dto.Document;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/documents")
@Transactional
public class DocumentControllerImpl implements DocumentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentControllerImpl.class);

    private final DocumentDao documentDao;

    @Autowired
    public DocumentControllerImpl(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    private static Document of(DocumentEntity entity) {
        Document document = new Document();
        document.setId(entity.getId());
        document.setCode(entity.getCode());
        document.setCreateDate(entity.getCreateDate());
        document.setName(entity.getName());
        return document;
    }

    @Override
    @GetMapping("/{id}")
    public Document getDocument(@PathVariable long id) {
        LOGGER.debug("Getting document with id = {}", id);
        DocumentEntity documentEntity = documentDao.getDocumentById(id);
        if (documentEntity == null) {
            throw new DocumentNotFoundException();
        }

        Document document = of(documentEntity);
        LOGGER.debug("Retrieved document {}", document);
        return document;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> addDocument(@RequestBody Document document) {
        LOGGER.debug("Adding document {}", document);

        if (document.getId() != null) {
            throw new IllegalArgumentException("You can not specify document identifier");
        }

        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setName(document.getName());
        documentEntity.setCode(document.getCode());
        documentEntity.setCreateDate(document.getCreateDate());

        documentDao.addDocument(documentEntity);
        Long id = documentEntity.getId();
        LOGGER.debug("Document with id = {} added", id);

        URI location = URI.create("/documents/" + id);
        return ResponseEntity.created(location).build();
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateDocument(@PathVariable long id, @RequestBody Document document) {
        LOGGER.debug("Updating document with id = {}, new content = {}", id, document);
        DocumentEntity documentEntity = documentDao.getDocumentById(id);
        if (documentEntity == null) {
            throw new DocumentNotFoundException();
        }

        documentEntity.setName(document.getName());
        documentEntity.setCode(document.getCode());
        documentEntity.setCreateDate(document.getCreateDate());

        documentDao.updateDocument(documentEntity);
        LOGGER.debug("Document with id = {} updated", id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteDocument(@PathVariable long id) {
        LOGGER.debug("Deleting document with id = {}", id);
        DocumentEntity documentEntity = documentDao.getDocumentById(id);
        if (documentEntity == null) {
            throw new DocumentNotFoundException();
        }

        documentDao.deleteDocument(documentEntity);
        LOGGER.debug("Document with id = {} deleted", id);
    }
}
