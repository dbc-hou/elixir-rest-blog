package com.example.restblog.web;

import com.example.restblog.data.*;
import com.example.restblog.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
//    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    public List<Post> getAll(OAuth2Authentication auth) {
        if (auth == null) {
            return pr.findAll();
        }
        User author = ur.findByEmail(auth.getName());
        return pr.getPostsByAuthor(author);
    }

    //
    @GetMapping("{postId}")
    public Post getByID(@PathVariable long postId) {
//        return new Post(postId, "Post " + postId, "Blah-blah-blah.",author1,1);
        return pr.getById(postId);
    }


    @PostMapping
    public void createPost(@RequestBody Post newPost, OAuth2Authentication auth) {
        String email = auth.getName(); // yes, the email is found under "getName()"
        User user = ur.findByEmail(email);
        newPost.setAuthor(user);
        //Persist the post to the DB
        pr.save(newPost);
        System.out.println("Post created.");
        ems.prepareAndSend(newPost, "Here's some news!", "You are not of the body!");
    }

    @PutMapping("{id}")
    public void updatePost(@PathVariable long id, @RequestBody Post thisPost) {
        Post postToUpdate = pr.getById(id);
//        postToUpdate.setTitle(thisPost.getTitle());
//        postToUpdate.setContent(thisPost.getContent());
        BeanUtils.copyProperties(thisPost, postToUpdate, getNullPropertyNames(thisPost));
        postToUpdate.setId(id);
        pr.save(postToUpdate);
        System.out.println("Post #" + id + " updated: " + thisPost);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable long id) {
        Post postToDelete = pr.getById(id);
        pr.delete(postToDelete);
        System.out.println("Post #" + id + " deleted.");
    }

    @GetMapping("searchPostsByCategory")
    public List<Post> searchPostsByCategory(@RequestParam String category) {
        return pr.getPostsByCategories(cr.findCategoryByName(category));
    }

    private List<Post> searchPostsByKeyword(@RequestParam String term) {
        return pr.searchByTitleLike(term);
    }

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
