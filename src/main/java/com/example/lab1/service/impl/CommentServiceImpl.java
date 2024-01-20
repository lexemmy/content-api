package com.example.lab1.service.impl;

import com.example.lab1.dto.request.CommentRequestDto;
import com.example.lab1.dto.response.CommentDto;
import com.example.lab1.helper.ListMapper;
import com.example.lab1.model.Comment;
import com.example.lab1.model.Post;
import com.example.lab1.model.User;
import com.example.lab1.repository.CommentRepository;
import com.example.lab1.repository.PostRepository;
import com.example.lab1.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Override
    public List<CommentDto> getAll() {
        return listMapper.mapList(commentRepository.findAll(), new CommentDto());
    }

    @Override
    public CommentDto getOne(Long commentId) {
        return modelMapper.map(commentRepository.findById(commentId), CommentDto.class);
    }

    @Override
    public void create(CommentRequestDto commentRequestDto) {

        Comment comment = new Comment();
        comment.setName(commentRequestDto.getName());

        Post post = postRepository.findById(commentRequestDto.getPostId()).orElse(null);

        comment.setPost(post);

        commentRepository.save(comment);
    }

    @Override
    public void update(Long commentId, Comment updatedComment) {

        Comment comment = commentRepository.findById(commentId).orElse(null);

        if(comment != null){
            comment.setName(updatedComment.getName());

            commentRepository.save(comment);
        }
    }

    @Override
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
