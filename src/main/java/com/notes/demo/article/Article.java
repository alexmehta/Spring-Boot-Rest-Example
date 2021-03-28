package com.notes.demo.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.notes.demo.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relationClass"})
@Table(name = "articles")
public class Article {
    @Id
    @SequenceGenerator(name = "idIncrement", sequenceName = "idIncrement", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idIncrement")
    private long Id;
    @Column
    private String text;

    @JsonIgnore
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Article(long id, String text, User user) {
        Id = id;
        this.text = text;
        this.user = user;
    }

    public Article() {

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
