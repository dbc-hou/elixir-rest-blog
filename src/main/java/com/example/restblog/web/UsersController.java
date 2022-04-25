package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/users", headers = "Accept=application/json")

public class UsersController {
    private final UsersRepository ur;
    private final PasswordEncoder pe;
    private Collection<Post> posts;

    public UsersController(UsersRepository ur, PasswordEncoder pe) {
        this.ur = ur;
        this.pe = pe;
    }

    private List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
//        users.add(new User(1L, "dbchou", "david.collins.hou@gmail.com","COYIin2022",null,User.Role.ADMIN));
//        users.add(new User(2L, "gisele82", "gisele82@msn.com", "GizzyWork21",null,User.Role.USER));
        return users;
    }

    @GetMapping("{userId}")
    private Optional<User> getByID(@PathVariable long userId) {
        return ur.findById(userId);
    }
    @GetMapping("username")
    private User getByUserName(@RequestParam String username) {
        return ur.findByUsername(username);
    }
    @GetMapping("email")
    private User getByEmail(@RequestParam String email) {
        return ur.findByEmail(email);
    }

    @GetMapping("me")
    private User getMyInfo(OAuth2Authentication auth) {
        String email = auth.getName(); // yes, the email is found under "getName()"
        return ur.findByEmail(email);
    }

    @PostMapping
    private void createUser(@RequestBody User newUser) {
        newUser.setRole(User.Role.USER);
        String encryptedPassword = newUser.getPassword();
        encryptedPassword = pe.encode(encryptedPassword);
        newUser.setPassword(encryptedPassword);
        ur.save(newUser);
    }

    @PutMapping("{id}")
    private void updateUser(@PathVariable long id, @RequestBody User thisUser) {
//        System.out.println("User #" + id + " has been updated." + thisUser);
//        ur.save(id, thisUser);
    }

    @PutMapping("{id}/password")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
        User u = ur.getById(id);
        oldPassword = u.getPassword();
        if (newPassword != oldPassword || newPassword.length() > 2) {
            u.setPassword(newPassword);
            ur.save(u);
        }
//        if (newPassword == oldPassword) {
//            System.out.println("Sorry, you may not repeat your previous password");
//        } else if (newPassword.length() <= 2) {
//            System.out.println("Please make sure that your password is at least 3 characters in length.");
//        } else {
//            System.out.println("Password for user #" + id + " has been updated.");
//        }
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable long id) {
        System.out.println("User #" + id + " has been deleted.");
    }

}
