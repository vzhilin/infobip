package ru.zhilin.example.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Some document.
 */
@Entity
@Table(name = "DOCUMENT")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_SEQ")
    @SequenceGenerator(name = "DOCUMENT_SEQ", sequenceName = "DOCUMENT_SEQ")
    private Long id;

    @NotNull
    private String code;

    private LocalDate createDate;

    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DocumentEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", createDate=" + createDate +
                ", name='" + name + '\'' +
                '}';
    }
}
