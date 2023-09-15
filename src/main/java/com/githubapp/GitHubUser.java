package com.githubapp;

import lombok.Data;

import java.util.List;

@Data
public class GitHubUser {
    private String login;
    private List<Repo> repos;


}
