package com.example.lab1.service;

import com.example.lab1.dto.request.CommentRequestDto;
import com.example.lab1.dto.response.CommentDto;
import com.example.lab1.dto.response.PostDto;
import com.example.lab1.dto.response.UserDto;
import com.example.lab1.model.Comment;
import com.example.lab1.model.User;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAll();

    CommentDto getOne(Long commentId);

    void create(CommentRequestDto commentRequestDto);

    void update(Long commentId, Comment updatedComment);

    void delete(Long commentId);
}
