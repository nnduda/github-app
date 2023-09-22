package com.githubapp;

import lombok.Data;

@Data
public class Repo {

    public String name;
    public boolean fork;
    public Owner owner;

}
