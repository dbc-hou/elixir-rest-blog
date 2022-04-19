package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalQueries.localDate;
@CrossOrigin
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
        newUser.setUsername("Joseph diMaggio");
        newUser.setEmail("jdimaggio@nyyankees.com");
        newUser.setPassword("M@rilyn123");
        return newUser;
    }
    @GetMapping("/username")
    private User getByUserName(@RequestParam String username) {
        User newUser = new User();
        newUser.setUsername(username);
        return newUser;
    }
    @GetMapping("/email")
    private User getByEmail(@RequestParam String email) {
        User newUser = new User();
        newUser.setEmail(email);
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

    @PutMapping("{id}/password")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @RequestParam String newPassword) {
        if (newPassword == oldPassword) {
            System.out.println("Sorry, you may not repeat your previous password");
        } else if (newPassword.length() <= 2) {
            System.out.println("Please make sure that your password is at least 3 characters in length.");
        } else {
            System.out.println("Password for user #" + id + " has been updated.");
        }
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable long id) {
        System.out.println("User #" + id + " has been deleted.");
    }

}
