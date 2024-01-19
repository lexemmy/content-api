package com.example.lab1.service;

import com.example.lab1.dto.response.PostDto;
import com.example.lab1.dto.response.UserDto;
import com.example.lab1.model.Post;
import com.example.lab1.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    List<UserDto> getUsersWithMoreThanNPosts(int postCount);

    UserDto getOne(Long userId);

    void create(User user);

    List<PostDto> getUserPosts(Long userId);

    void update(Long userId, User updatedUser);

    void delete(Long userId);

}
