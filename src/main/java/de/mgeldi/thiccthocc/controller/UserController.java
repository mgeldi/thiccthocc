package de.mgeldi.thiccthocc.controller;

import de.mgeldi.thiccthocc.exceptions.UserAlreadyExistsException;
import de.mgeldi.thiccthocc.exceptions.UserNotFoundException;
import de.mgeldi.thiccthocc.model.User;
import de.mgeldi.thiccthocc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<User> getUserById(@PathVariable("username") String username) throws UserNotFoundException {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws UserAlreadyExistsException {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable("username") String username) throws UserNotFoundException {
        User updatedUser = userService.updateUser(user, username);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
