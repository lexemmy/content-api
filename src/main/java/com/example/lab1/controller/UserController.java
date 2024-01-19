package com.example.lab1.controller;

import com.example.lab1.dto.response.PostDto;
import com.example.lab1.dto.response.UserDto;
import com.example.lab1.model.Post;
import com.example.lab1.model.User;
import com.example.lab1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping
    public List<UserDto> getAll(@RequestParam(name = "minPosts", required = false) Integer minPosts){
        return minPosts != null ? userService.getUsersWithMoreThanNPosts(minPosts) : userService.getAll();
    }

    @GetMapping("/{userId}")
    public UserDto getOne(@PathVariable Long userId){
        return userService.getOne(userId);
    }

    @GetMapping("/{userId}/posts")
    public List<PostDto> getUserPosts(@PathVariable Long userId){
        return userService.getUserPosts(userId);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody User user) {
        userService.update(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
