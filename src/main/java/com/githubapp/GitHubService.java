package com.githubapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class GitHubService {
    private static final String GITHUB_URL = "https://api.github.com/users/{username}";
    private static final String GITHUB_REPOS_URL = "https://api.github.com/users/{username}/subscriptions";

    private static final String GITHUB_BRANCH_URL = "https://api.github.com/repos/{username}/{repoName}/branches";
    RestTemplate restTemplate = new RestTemplate();

    // moze powinien zwrócic obiekt githubuser?
    public String getUser(String username){
        String response = restTemplate.getForObject(GITHUB_URL, String.class, username);
        User user = restTemplate.getForObject(GITHUB_URL, User.class, username);


        log.info(response);
        return response;
    }

    public List<Repo> getRepos(String username){
        Repo[] repos = restTemplate.getForObject(GITHUB_REPOS_URL, Repo[].class, username);
        for(Repo repo : repos) {
            if (repo.isFork() == false) {
                if (!repo.getOwner().getLogin().equals(username)) {
                    Branch[] branches = restTemplate.getForObject(GITHUB_BRANCH_URL, Branch[].class, repo.getOwner().getLogin(), repo.getName());
                    repo.setBranches(branches);
                    System.out.println(Arrays.deepToString(branches));
                } else {
                    Branch[] branches = restTemplate.getForObject(GITHUB_BRANCH_URL, Branch[].class, username, repo.getName());
                    repo.setBranches(branches);
                    System.out.println(Arrays.deepToString(branches));
                }
            }
        }

        return List.of(repos);
    }

}
