package com.notes.demo.user;

import com.notes.demo.article.Article;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "idIncrement", sequenceName = "idIncrement", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idIncrement")
    @Column(name = "User_id")
    private long Id;
    @Column()
    private String username;
    @Column()
    private String password;
    @Column()
    private LocalDate creationtime = LocalDate.now();

    @Transient
    private Integer age;


    @Column(name = "article_list")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Article> articles;


    @Column()
    @ColumnDefault("false")
    private boolean premium;
    @Column()
    @ColumnDefault("false")
    private boolean delete;


    public User() {
    }

    public User(long id, String username, String password, LocalDate creationtime,  List<Article> articles, boolean premium, boolean delete) {
        Id = id;
        this.username = username;
        this.password = password;
        this.creationtime = creationtime;
        this.articles = articles;
        this.premium = premium;
        this.delete = delete;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(LocalDate creationtime) {
        this.creationtime = creationtime;
    }

    public Integer getAge() {
        return Period.between(creationtime,LocalDate.now()).getDays();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
