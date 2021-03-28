package com.notes.demo.article;

import com.notes.demo.user.User;
import com.notes.demo.user.UserRepo;
import exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//TODO fix null on user end
@RequestMapping("/articles/api")
public class ArticleController {
    @Autowired
    ArticleRepo articleRepo;
    @Autowired
    UserRepo userRepo;
    @GetMapping("/getArticleList/")
    public List<Article> getAllUsers(){
        return articleRepo.findAll();
    }
    @GetMapping("/article/{id}")
    public Optional<Article> articleView(@PathVariable(value = "id") long id){
        return articleRepo.findById(id);
    }

    @PostMapping("/article/create/{author}")
    public Article addArticle(@RequestBody Article article, @PathVariable(value = "author") long author) throws ResourceNotFoundException {
        userRepo.findById(author).map(user -> user.getArticles().add(article)
        );

        userRepo.save(userRepo.findById(author).get());
        return userRepo.findById(author).map(user -> {
                article.setUser(user);
        return articleRepo.save(article);
        }).orElseThrow(()->new ResourceNotFoundException("Sorry, Not found"));
    }



}
