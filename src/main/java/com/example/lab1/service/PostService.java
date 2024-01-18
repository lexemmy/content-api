package com.example.lab1.service;

import com.example.lab1.dto.response.PostDto;
import com.example.lab1.model.Post;

import java.util.List;

public interface PostService {

    List<PostDto> getAll();

    List<PostDto> searchByAuthor(String keyword);

    List<PostDto> getByAuthor(String author);

    void create(Post post);

    PostDto getOne(Long postId);

    void update(Long postId, Post post);

    void delete(Long postId);
}
