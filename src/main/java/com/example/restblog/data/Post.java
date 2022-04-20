package com.example.restblog.data;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="posts")

//import javax.validation.constraints.NotNull;

public class Post {

    //Instance variables mapping to table columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
//    private User author;
//    private Collection<Category> categories;

//    public Collection<Category> getCategories() {
//        return this.categories;
//    }
//
//    private void setCategories(Collection<Category> categories) {
//        this.categories = categories;
//    }
}
