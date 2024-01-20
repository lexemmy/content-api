package com.example.lab1.controller;

import com.example.lab1.dto.response.CommentDto;
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
    public List<UserDto> getAll(
            @RequestParam(name = "minPosts", required = false) Integer minPosts,
            @RequestParam(name = "title", required = false) String title){
        List<UserDto> users;
        if (minPosts != null) {
            users = userService.getUsersWithMoreThanNPosts(minPosts);
        } else if (title != null) {
            users = userService.findUsersByPostTitle(title);
        } else {
            users = userService.getAll();
        }
        return users;
    }

    @GetMapping("/{userId}")
    public UserDto getOne(@PathVariable Long userId){
        return userService.getOne(userId);
    }

    @GetMapping("/{userId}/posts")
    public List<PostDto> getUserPosts(@PathVariable Long userId){
        return userService.getUserPosts(userId);
    }

    @GetMapping("/{userId}/posts/{postId}/comments/{commentId}")
    public CommentDto getUserCommentById(@PathVariable Long userId, @PathVariable Long postId, @PathVariable Long commentId){
        return userService.getUserCommentById(userId,postId, commentId);
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
