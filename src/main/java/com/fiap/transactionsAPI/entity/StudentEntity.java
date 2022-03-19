package com.fiap.transactionsAPI.entity;

import com.fiap.transactionsAPI.dto.StudentDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document("student")
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private String id;

    private String name;
    private String email;
    private Long ra;

    @DBRef(lazy = true)
    private CardEntity card;

    public StudentEntity() {
    }

    public StudentEntity(StudentDTO studentDTO) {
        this.ra = studentDTO.getRa();
        this.name = studentDTO.getName();
        this.email = studentDTO.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(id, that.id) && Objects.equals(ra, that.ra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, id, ra);
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", ra=" + ra +
                '}';
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }
}
