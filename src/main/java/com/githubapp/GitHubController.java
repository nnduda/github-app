package com.githubapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("users")
public class GitHubController {


    private GitHubService service;

    public GitHubController(GitHubService service) {
        this.service = service;
    }


    @GetMapping(value = "/username")
    @ResponseBody
    public String getUser(@RequestParam(value = "username") String username){
        return service.getUser(username);
    }

    @GetMapping(value = "/repos")
    @ResponseBody
    public List<Repo> getRepos(@RequestParam(value = "username") String username){
        return service.getRepos(username);
    }
}

