package com.example.lab1.controller;

import com.example.lab1.dto.request.CommentRequestDto;
import com.example.lab1.dto.request.PostRequestDto;
import com.example.lab1.dto.response.CommentDto;
import com.example.lab1.dto.response.PostDto;
import com.example.lab1.dto.response.UserDto;
import com.example.lab1.service.CommentService;
import com.example.lab1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<CommentDto> getAll() {
        return commentService.getAll();

    }

    @GetMapping("/{commentId}")
    public CommentDto getOne(@PathVariable Long commentId){
        return commentService.getOne(commentId);
    }

    @PostMapping
    public void createPost(@RequestBody CommentRequestDto commentRequestDto) {
        commentService.create(commentRequestDto);
    }


    @DeleteMapping("/{commentId}")
    public void deletePost(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }
}
