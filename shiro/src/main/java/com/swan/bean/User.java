package com.swan.bean;

public class User {

    private String id;
    private String username;
    private String password;
    private String email;
    private String permission;

    public User() {
    }

    public User(String id, String username, String password, String email, String permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.permission = permission;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
