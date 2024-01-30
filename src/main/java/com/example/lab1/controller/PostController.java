package com.example.lab1.controller;

import com.example.lab1.dto.request.PostRequestDto;
import com.example.lab1.dto.response.PostDto;
import com.example.lab1.dto.response.UserDto;
import com.example.lab1.model.Post;
import com.example.lab1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public List<EntityModel<PostDto>> getAll(@RequestParam(name = "title", required = false) String title) {

        List<PostDto> posts = (title != null) ? postService.findByTitle(title) : postService.getAll();

        return posts.stream()
                .map(this::addLinks)
                .collect(Collectors.toList());
    }

    @GetMapping("/{postId}")
    public PostDto getOne(@PathVariable Long postId){
        return postService.getOne(postId);
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostRequestDto post) {
       return  postService.create(post);
    }


    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.delete(postId);
    }

    private EntityModel<PostDto> addLinks(PostDto postDto) {
        EntityModel<PostDto> model = EntityModel.of(postDto);

        Link selfLink = WebMvcLinkBuilder.linkTo(PostController.class)
                .slash(postDto.getId())
                .withSelfRel();
        model.add(selfLink);

        return model;
    }

}
