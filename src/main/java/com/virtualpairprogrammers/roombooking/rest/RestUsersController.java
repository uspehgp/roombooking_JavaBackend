package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class RestUsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping
    public User newUser(User user) {
        return userRepository.save(user);
    }

    @PutMapping
    public User update(User updatedUser) {
        User originalUser = userRepository.findById(updatedUser.getId()).get();
        originalUser.setName(updatedUser.getName());
        return userRepository.save(originalUser);
    }
}
