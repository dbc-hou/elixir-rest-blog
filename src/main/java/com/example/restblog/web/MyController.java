package com.example.restblog.web;

import com.example.restblog.data.*;
import com.example.restblog.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/posts", headers = "Accept=application/json")
public class MyController {
    private final UsersRepository ur;
    private final PostsRepository pr;
    private final CategoriesRepository cr;
    private final EmailService ems;

    @GetMapping
    private List<Post> getAll() {

//        posts.add(new Post(1L, "Post 1", "Content goes here.",author1));
//        posts.add(new Post(2L, "Post 2", "More content goes here.",author1));
//        posts.add(new Post(3L, "Post 3", "A little more content goes here.",author2));
        return pr.findAll();
    }

    //
    @GetMapping("{postId}")
    private Post getByID(@PathVariable long postId) {
//        return new Post(postId, "Post " + postId, "Blah-blah-blah.",author1,1);
        return pr.getById(postId);
    }


    @PostMapping
    private void createPost(@RequestBody Post newPost, OAuth2Authentication auth) {
        String email = auth.getName(); // yes, the email is found under "getName()"
        User user = ur.findByEmail(email);
        newPost.setAuthor(user);
        //Persist the post to the DB
        pr.save(newPost);
        System.out.println("Post created.");
        ems.prepareAndSend(newPost, "Here's some news!", "You are not of the body!");
    }

    @PutMapping("{id}")
    private void updatePost(@PathVariable long id, @RequestBody Post thisPost) {
        Post postToUpdate = pr.getById(id);
        postToUpdate.setTitle(thisPost.getTitle());
        postToUpdate.setContent(thisPost.getContent());
        pr.save(postToUpdate);
        System.out.println("Post #" + id + " updated: " + thisPost);
    }

    @DeleteMapping("{id}")
    private void deletePost(@PathVariable long id) {
        Post postToDelete = pr.getById(id);
        pr.delete(postToDelete);
        System.out.println("Post #" + id + " deleted.");
    }

    @GetMapping("searchPostsByCategory")
    private List<Post> searchPostsByCategory(@RequestParam String category) {
        return pr.getPostsByCategories(cr.findCategoryByName(category));
    }

    private List<Post> searchPostsByKeyword(@RequestParam String term) {
        return pr.searchByTitleLike(term);
    }
}
