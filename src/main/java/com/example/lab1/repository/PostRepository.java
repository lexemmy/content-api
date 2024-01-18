package com.example.lab1.repository;

import com.example.lab1.model.Post;

import java.util.List;

public interface PostRepository {

    List<Post> findAll();

    void save(Post post);

    Post findById(Long postId);

    List<Post> findByAuthor(String author);

    List<Post> searchByAuthor(String keyword);

    void update(Long postId, Post post);

    void delete(Long postId);
}
