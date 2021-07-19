package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.AngularUser;
import com.virtualpairprogrammers.roombooking.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class RestUsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<AngularUser> getAllUsers() {
        return userRepository.findAll().parallelStream().map(user -> new AngularUser(user)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AngularUser getUser(@PathVariable("id") long id) {
        return new AngularUser(userRepository.findById(id).get());
    }

    @PostMapping
    public AngularUser newUser(@RequestBody AngularUser user) {
        return new AngularUser(userRepository.save(user.asUser()));
    }

    @PutMapping
    public AngularUser update(@RequestBody AngularUser updatedUser) {
        User originalUser = userRepository.findById(updatedUser.getId()).get();
        originalUser.setName(updatedUser.getName());
        return new AngularUser(userRepository.save(originalUser));
    }
}
