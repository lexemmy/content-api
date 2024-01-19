package com.example.lab1.controller;

import com.example.lab1.dto.response.PostDto;
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
    public List<EntityModel<PostDto>> getAll(@RequestParam(required = false) String author,
                                @RequestParam(required = false) String search) {
        List<PostDto> posts = (author != null) ? postService.getByAuthor(author) :
                                (search != null) ? postService.searchByAuthor(search) :
                                    postService.getAll();

        return posts.stream()
                .map(this::addLinks)
                .collect(Collectors.toList());
    }

    @GetMapping("/byAuthor")
    public List<PostDto> getPostsByAuthor(@RequestParam("author") String author) {
        return postService.getByAuthor(author);
    }

    @GetMapping("/{postId}")
    public PostDto getOne(@PathVariable Long postId) {
        return postService.getOne(postId);
    }

    @PostMapping
    public void createPost(@RequestBody Post post) {
        postService.create(post);
    }

    @PutMapping("/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody Post post) {
        postService.update(postId, post);
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
