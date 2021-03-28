package com.notes.demo;

import com.notes.demo.article.ArticleRepo;
import com.notes.demo.user.User;
import com.notes.demo.user.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
public class DataPopulationTets {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ArticleRepo articleRepo;

    @Test
    void addArticles(){
    }

}
