package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {

    List<Post> getPostsByCategory(Category category);

    List<Post> searchByTitleLike(@RequestParam String keyword);

    List<Post> getPostsByAuthor(User author);

}
