package com.example.restblog.web;

import com.example.restblog.data.Post;
//import com.example.restblog.data.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value="/api/posts", headers="Accept=application/json")
public class PostsController {

    @GetMapping
    private List<Post>getAll() {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Post 1", "Content goes here."));
        posts.add(new Post(2L, "Post 2", "More content goes here."));
        posts.add(new Post(3L, "Post 3", "A little more content goes here."));
        return posts;
    }

    @GetMapping("{postId}")
    private Post getByID(@PathVariable long postId) {
        return new Post(postId, "Post " + postId, "Blah-blah-blah.");
    }
    @PostMapping
    private void createPost(@RequestBody Post newPost) {
        System.out.println("Ready to add post: " + newPost);
    }

    @PutMapping("{id}")
    private void updatePost(@PathVariable long id, @RequestBody Post thisPost) {
        System.out.println("Post #" + id + " updated: " + thisPost);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable long id) {
        System.out.println("Post #" + id + " deleted.");
    }

//    @PostMapping
//    private void createPost(@RequestBody Post newPost) {
//        try {
//            postRepository.save(newPost);
//        } catch (Exception ex) {
//            System.out.println(ex.getLocalizedMessage());
//        }
//    }
}
