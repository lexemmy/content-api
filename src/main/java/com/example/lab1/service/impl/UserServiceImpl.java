package com.example.lab1.service.impl;

import com.example.lab1.dto.response.PostDto;
import com.example.lab1.dto.response.UserDto;
import com.example.lab1.helper.ListMapper;
import com.example.lab1.model.Post;
import com.example.lab1.model.User;
import com.example.lab1.repository.UserRepository;
import com.example.lab1.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ListMapper listMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserDto> getAll() {
        return listMapper.mapList(userRepository.findAll(), new UserDto());
    }

    @Override
    public List<UserDto> getUsersWithMoreThanNPosts(int postCount) {
        return listMapper.mapList(userRepository.findByPostsSizeGreaterThan(postCount), new UserDto());
    }

    @Override
    public UserDto getOne(Long userId) {
        return modelMapper.map(userRepository.findById(userId).orElse(null), UserDto.class);
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public List<PostDto> getUserPosts(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<Post> posts = user.getPosts();
        return listMapper.mapList(posts, new PostDto());
    }

    @Override
    public void update(Long userId, User updatedUser) {
        User user = userRepository.findById(userId).orElse(null);

        if(user != null){
            user.setName(updatedUser.getName());

            userRepository.save(user);
            System.out.println(user);
        }
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

}
