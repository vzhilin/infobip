package ru.zhilin.example.openapi.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.time.LocalDate;

/**
 * Some document.
 */
@JsonRootName("document")
public class Document {
    private Long id;

    private String code;

    private LocalDate createDate;

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
        return "Document{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", createDate=" + createDate +
                ", name='" + name + '\'' +
                '}';
    }
}

