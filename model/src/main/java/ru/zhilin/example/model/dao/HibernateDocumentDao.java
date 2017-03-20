package ru.zhilin.example.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.zhilin.example.model.entity.DocumentEntity;

@Component
public class HibernateDocumentDao implements DocumentDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateDocumentDao.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateDocumentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public DocumentEntity getDocumentById(long id) {
        LOGGER.debug("Getting document by id {}", id);
        Session session = sessionFactory.getCurrentSession();
        DocumentEntity document = session.get(DocumentEntity.class, id);
        LOGGER.debug("Retrieved document {}", document);
        return document;
    }

    @Override
    public void addDocument(DocumentEntity document) {
        LOGGER.debug("Adding document {}", document);
        Session session = sessionFactory.getCurrentSession();
        session.save(document);
        LOGGER.debug("Document {} added", document);
    }

    @Override
    public void updateDocument(DocumentEntity document) {
        LOGGER.debug("Updating document {}", document);
        Session session = sessionFactory.getCurrentSession();
        session.update(document);
        LOGGER.debug("Document {} updated", document);
    }

    @Override
    public void deleteDocument(DocumentEntity document) {
        LOGGER.debug("Deleting document {}", document);
        Session session = sessionFactory.getCurrentSession();
        session.delete(document);
        LOGGER.debug("Document {} deleted", document);
    }
}
