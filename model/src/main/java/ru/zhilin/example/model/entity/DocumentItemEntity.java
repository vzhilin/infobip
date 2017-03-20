package ru.zhilin.example.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Some document item.
 */
@Entity
@Table(name = "DOCUMENT_ITEM")
public class DocumentItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_ITEM_SEQ")
    @SequenceGenerator(name = "DOCUMENT_ITEM_SEQ", sequenceName = "DOCUMENT_ITEM_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private DocumentEntity document;

    @NotNull
    private String name;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "DocumentItemEntity{" +
                "id=" + id +
                ", document=" + document +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
