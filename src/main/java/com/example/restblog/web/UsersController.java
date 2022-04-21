package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/users", headers = "Accept=application/json")

public class UsersController {
    private final UsersRepository ur;
    private Collection<Post> posts;

    public UsersController(UsersRepository ur) {
        this.ur = ur;
    }

    private List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
//        users.add(new User(1L, "dbchou", "david.collins.hou@gmail.com","COYIin2022",null,User.Role.ADMIN));
//        users.add(new User(2L, "gisele82", "gisele82@msn.com", "GizzyWork21",null,User.Role.USER));
        return users;
    }

    @GetMapping("{userId}")
    private User getByID(@PathVariable long userId) {
        User newUser = new User();
        newUser.setId(userId);
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
        newUser.setRole(User.Role.USER);
        ur.save(newUser);
    }

    @PutMapping("{id}")
    private void updateUser(@PathVariable long id, @RequestBody User thisUser) {
        System.out.println("User #" + id + " has been updated." + thisUser);
    }

    @PutMapping("{id}/password")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
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
