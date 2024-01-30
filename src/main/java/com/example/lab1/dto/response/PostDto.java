package com.example.lab1.dto.response;

import com.example.lab1.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"user"})
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private User user;
    private String userEmail;

    public PostDto(PostDto postDto) {
        this.id = postDto.getId();
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.author = postDto.getAuthor();
        this.userEmail = postDto.getUser() != null ? postDto.getUser().getEmail() : null;
    }
}
