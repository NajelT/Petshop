package com.buseduc.javacourse.petshop.users;

public abstract class User {
    private String name;
    public User() {}
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


}
