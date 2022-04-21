package com.example.restblog.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

import static java.time.temporal.TemporalQueries.localDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")

public class User {

    public enum Role {USER, ADMIN}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 32)
    private String username;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 32)
    private String password;
    @Column(nullable = true)
    private Date createdAt;
    @Column(nullable = false)
    private Role role;
    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    private Collection<Post> posts;
}