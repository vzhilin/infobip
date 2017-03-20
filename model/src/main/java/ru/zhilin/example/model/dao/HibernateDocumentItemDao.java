package ru.zhilin.example.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import ru.zhilin.example.model.entity.DocumentEntity;
import ru.zhilin.example.model.entity.DocumentItemEntity;

@Component
public class HibernateDocumentItemDao implements DocumentItemDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateDocumentItemDao.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateDocumentItemDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public DocumentItemEntity getDocumentItemById(DocumentEntity documentDto, long itemId) {
        Session session = sessionFactory.getCurrentSession();
        return (DocumentItemEntity) session.createQuery("FROM DocumentItemEntity WHERE document = ? AND id = ?")
                .setParameter(0, documentDto)
                .setParameter(1, itemId)
                .uniqueResult();
    }

    @Override
    public List<DocumentItemEntity> getDocumentItems(DocumentEntity documentDto) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM DocumentItemEntity WHERE document = ?")
                .setParameter(0, documentDto)
                .list();
    }

    @Override
    public void addDocumentItem(DocumentItemEntity item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    @Override
    public void updateDocumentItem(DocumentItemEntity item) {
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
    }

    @Override
    public void deleteDocumentItem(DocumentItemEntity item) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }
}
