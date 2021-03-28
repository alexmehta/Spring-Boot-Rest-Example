package com.notes.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select c from User c " + "where lower(c.username) like lower(concat('%',:searchTerm,'%'))")
    List<User> search(@Param("searchTerm") String searchTerm);
}
