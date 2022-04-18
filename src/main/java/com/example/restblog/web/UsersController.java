package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalQueries.localDate;

@RestController
@RequestMapping(value = "api/users", headers = "Accept=application/json")
public class UsersController {
    private List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1L, "dbchou", "david.collins.hou@gmail.com","COYIin2022",User.Role.ADMIN));
        users.add(new User(2L, "gisele82", "gisele82@msn.com", "GizzyWork21",User.Role.USER));
        return users;
    }

    @GetMapping("{userId}")
    private User getByID(@PathVariable long userId) {
        User newUser = new User();
        newUser.setId(userId);
        return newUser;
    }

    @PostMapping
    private void createUser(@RequestBody User newUser) {
        System.out.println("User # " + newUser.getUsername() + " has just been added.");
    }

    @PutMapping("{id}")
    private void updateUser(@PathVariable long id, @RequestBody User thisUser) {
        System.out.println("User #" + id + " has been updated." + thisUser);
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable long id) {
        System.out.println("User #" + id + " has been deleted.");
    }

}
