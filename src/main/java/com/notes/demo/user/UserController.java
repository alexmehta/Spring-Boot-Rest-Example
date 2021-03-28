
package com.notes.demo.user;

import com.notes.demo.article.Article;
import com.notes.demo.article.ArticleRepo;
import exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepo repo;

    @Autowired
    ArticleRepo articleRepo;

    @GetMapping("/getUserList")
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @GetMapping("/getUserList/{id}")
    public Optional<User> getUserbyId(@PathVariable(value = "id") Long id) {
        return repo.findById(id);
    }

    @PostMapping("/newuser")
    public User addUser(@PathVariable(value = "user") User user) {
        return repo.save(user);
    }

    @PutMapping("/updateuser/{id}")
    public User updateUser(@PathVariable(value = "id") long id, @RequestBody User userdetails) {
        Optional<User> user = repo.findById(id);
        User newUser = user.get();
        newUser.setUsername(userdetails.getUsername());
        newUser.setPassword(userdetails.getPassword());
        return repo.save(newUser);
    }

    @DeleteMapping("/getUserList/delete/{id}")
    public void deletion(@PathVariable(value = "id") Long id) {
        Optional<User> user = repo.findById(id);
        User userDelete = user.get();
        userDelete.setArticles(null);
        repo.delete(userDelete);
    }

    @GetMapping("/getUserData/{id}/articles")
    public List<Article> getAllUsersArticles(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        User user = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found sorry have a nice day"));
        return user.getArticles();

    }

    @GetMapping("/filter/getuserdata")
    public List<User> findAll(@PathVariable(value = "stringFilter") String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return repo.findAll();
        } else {
            return repo.search(stringFilter);
        }
    }
    @GetMapping("/count")
    public long count() {
        return repo.count();
    }

}

