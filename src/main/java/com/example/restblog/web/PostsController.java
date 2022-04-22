package com.example.restblog.web;

import com.example.restblog.data.*;
//import com.example.restblog.data.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value="/api/posts", headers="Accept=application/json")
public class PostsController {
    private final PostsRepository pr;
    private final UsersRepository ur;
    private final CategoriesRepository cr;
//    private static final User author1 = new User(1L,"donalddux","ddux@disney.com","",null, User.Role.USER,null);
//    private static final User author2 = new User(2L,"mickeymus","mmus@disney.com","",null, User.Role.ADMIN,null);


    public PostsController(PostsRepository pr, UsersRepository ur, CategoriesRepository cr) {
        this.pr = pr;
        this.ur = ur;
        this.cr = cr;
    }

    @GetMapping
    private List<Post>getAll() {

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
    private void createPost(@RequestBody Post newPost) {
        newPost.setAuthor(ur.getById(2L));
        List<Category> categories = new ArrayList<>();
        categories.add(cr.findCategoryByName("soccer"));
        categories.add(cr.findCategoryByName("literature"));
        newPost.setCategories(categories);
    //Persist the post to the DB
        pr.save(newPost);
        System.out.println("Post created.");
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
//    @PostMapping
//    private void createPost(@RequestBody Post newPost) {
//        try {
//            postRepository.save(newPost);
//        } catch (Exception ex) {
//            System.out.println(ex.getLocalizedMessage());
//        }
//    }
}
