package com.githubapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@Slf4j
public class GitHubService {
    private static final String GITHUB_URL = "https://api.github.com/users/";
    RestTemplate restTemplate = new RestTemplate();

    // moze powinien zwrócic obiekt githubuser?
    public String getUser(String username){
        String response = restTemplate.getForObject(GITHUB_URL + "{username}", String.class,username);
        log.info(response);
        return response;
    }
}
