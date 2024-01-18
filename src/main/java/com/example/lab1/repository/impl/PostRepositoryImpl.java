package com.example.lab1.repository.impl;

import com.example.lab1.model.Post;
import com.example.lab1.repository.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository  {

    private static List<Post> posts;

    static {
        posts = new ArrayList<>();

        Post p1 = new Post(1L, "First Post", "Content of the first post", "Author1");
        Post p2 = new Post(2L, "Second Post", "Content of the second post", "Author2");
        Post p3 = new Post(3L, "Third Post", "Content of the third post", "Author3");

        posts.add(p1);
        posts.add(p2);
        posts.add(p3);
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public void save(Post post) {
        posts.add(post);
    }

    @Override
    public Post findById(Long postId) {
        for (Post post : posts) {
            if (post.getId().equals(postId)) {
                System.out.println(post);
                return post;
            }
        }
        return null;
    }

    @Override
    public List<Post> findByAuthor(String author) {
        return posts.stream()
                .filter(post -> author.equals(post.getAuthor()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> searchByAuthor(String keyword) {
        return posts.stream()
                .filter(post -> post.getAuthor().contains(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long postId, Post updatedPost) {
        for (Post post : posts) {
            if (post.getId().equals(postId)) {
                post.setTitle(updatedPost.getTitle());
                post.setContent(updatedPost.getContent());
                post.setAuthor(updatedPost.getAuthor());
                return;
            }
        }

    }

    @Override
    public void delete(Long postId) {
        posts.removeIf(post -> post.getId().equals(postId));
    }
}
