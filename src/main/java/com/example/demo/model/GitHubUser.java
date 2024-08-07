package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GitHubUser {
    private Long id;
    private String login;
    private String name;
    private String email;
    private String avatarUrl;

    // Getters and Setters
}
