package com.example.lab1.service.impl;

import com.example.lab1.dto.response.PostDto;
import com.example.lab1.helper.ListMapper;
import com.example.lab1.model.Post;
import com.example.lab1.repository.PostRepository;
import com.example.lab1.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;


    @Override
    public List<PostDto> getAll() {
        return listMapper.mapList(postRepository.findAll(), new PostDto());
    }

    @Override
    public List<PostDto> searchByAuthor(String keyword) {
        return listMapper.mapList(postRepository.searchByAuthor(keyword), new PostDto());
    }

    @Override
    public List<PostDto> getByAuthor(String author) {
        return listMapper.mapList(postRepository.findByAuthor(author), new PostDto());
    }

    @Override
    public void create(Post post) {
        postRepository.save(post);
    }

    @Override
    public PostDto getOne(Long postId) {
        return modelMapper.map(postRepository.findById(postId), PostDto.class);
    }

    @Override
    public void update(Long postId, Post post) {
        postRepository.update(postId, post);
    }

    @Override
    public void delete(Long postId) {
    postRepository.delete(postId);
    }
}
