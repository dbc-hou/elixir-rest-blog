package com.example.restblog.data;

import lombok.*;

import java.util.Collection;
import java.util.Date;

import static java.time.temporal.TemporalQueries.localDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public enum Role {USER, ADMIN}
    private long id;
    private String username;
    private String email;
    private String password;
    private Date createdAt;
    private Role role;
    private Collection<Post> posts;
}