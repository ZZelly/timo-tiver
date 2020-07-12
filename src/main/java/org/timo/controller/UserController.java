package org.timo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.timo.model.user.User;
import org.timo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<User>();
            userRepository.findAll().forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        Optional<User> useData = userRepository.findByEmail(email);

        if (useData.isPresent()) {
            return new ResponseEntity<>(useData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//
//    @GetMapping("/{username}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
//        Optional<User> useData = userRepository.findByUsername(username);
//
//        if (useData.isPresent()) {
//            return new ResponseEntity<>(useData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/{name}")
//    public ResponseEntity<User> getUserByName(@PathVariable("name") String name) {
//        Optional<User> useData = userRepository.findByName(name);
//
//        if (useData.isPresent()) {
//            return new ResponseEntity<>(useData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("test");
        try {
            User _user = userRepository.save(new User(UUID.randomUUID(), user.getEmail(), user.getPassword(), user.getUsername(), user.getName()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(@PathVariable("email") String email, @RequestBody User user) {
        Optional<User> userData = userRepository.findByEmail(email);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setEmail(user.getEmail());
            _user.setPassword(user.getPassword());
            _user.setUsername(user.getUsername());
            _user.setName(user.getName());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("email") String email) {
        try {
            userRepository.deleteByEmail(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}