package com.example.lab1.service.impl;

import com.example.lab1.dto.request.PostRequestDto;
import com.example.lab1.dto.response.PostDto;
import com.example.lab1.helper.ListMapper;
import com.example.lab1.model.Post;
import com.example.lab1.model.User;
import com.example.lab1.repository.PostRepository;
import com.example.lab1.repository.UserRepository;
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
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;


    @Override
    public List<PostDto> getAll() {
        return listMapper.mapList(postRepository.findAll(), new PostDto());
    }

    @Override
    public List<PostDto> findByTitle(String title) {
        return listMapper.mapList(postRepository.findByTitle(title), new PostDto());
    }


    @Override
    public void create(PostRequestDto postRequestDto){
    Post post = new Post();
    post.setTitle(postRequestDto.getTitle());
    post.setContent(postRequestDto.getContent());
    post.setAuthor(postRequestDto.getAuthor());

    User user = userRepository.findById(postRequestDto.getUserId()).orElse(null);

    post.setUser(user);

    postRepository.save(post);

    }

    @Override
    public PostDto getOne(Long postId) {
        return modelMapper.map(postRepository.findById(postId), PostDto.class);
    }


    @Override
    public void delete(Long postId) {
    postRepository.deleteById(postId);
    }
}
