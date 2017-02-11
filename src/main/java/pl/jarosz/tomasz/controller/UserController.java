package pl.jarosz.tomasz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jarosz.tomasz.dao.entity.User;
import pl.jarosz.tomasz.service.UserService;

import java.util.List;

/**
 * Created by Tomek on 04.02.2017.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
