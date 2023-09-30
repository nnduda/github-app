package com.githubapp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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
    public ResponseEntity<Object> getRepos(@RequestHeader(HttpHeaders.ACCEPT) String accept, @RequestParam(value = "username") String username) {
        System.out.println(accept); // TODO sprawdzic czy w accept jest application/xml i jesli tak to zwrocic blad
        // czy nie ma
        try{
        if(accept ==("application/json"));

        } catch (HttpClientErrorException e) {
            Error error = new Error();
            error.setStatus(e.getStatusCode().value());
            error.setMessage(e.getStatusText());
            return ResponseEntity.status(406).body(error);
        }

        try {
            return ResponseEntity.ok(service.getRepos(username));
        } catch (HttpClientErrorException e) {
            Error error = new Error();
            error.setStatus(e.getStatusCode().value());
            error.setMessage(e.getStatusText());
            return ResponseEntity.status(404).body(error);
        }
    }
}

