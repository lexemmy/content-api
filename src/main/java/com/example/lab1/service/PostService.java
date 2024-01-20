package com.example.lab1.service;

import com.example.lab1.dto.request.PostRequestDto;
import com.example.lab1.dto.response.PostDto;
import com.example.lab1.model.Post;

import java.util.List;

public interface PostService {

    List<PostDto> getAll();

    List<PostDto> findByTitle(String title);

    void create(PostRequestDto post);

    PostDto getOne(Long postId);

    void delete(Long postId);
}
