package com.example.restblog.data;

import java.util.Date;

import static java.time.temporal.TemporalQueries.localDate;

public class User {
    private long id;
    private String username;
    private String email;
    private String password;
    private Date createdAt = (Date) localDate();
    public enum Role {USER, ADMIN};

    public User() {

    }

    public User(long id, String username,
                String email, String password,
                Role role) {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
