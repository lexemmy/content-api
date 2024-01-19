package com.example.lab1.repository;

import com.example.lab1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE SIZE(u.posts) > :minPosts")
    List<User> findByPostsSizeGreaterThan(int minPosts);
}
