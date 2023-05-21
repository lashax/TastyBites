package com.tsu.tastybites.controller;

import com.tsu.tastybites.entity.User;
import com.tsu.tastybites.model.LoginResponse;
import com.tsu.tastybites.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * რეგისტრაციისას მომხმარებლის შენახვა.
     */
    @PostMapping(path = "/save")
    public String saveUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * მომხმარებლის შესვლა (დალოგინება) იმეილისა და პაროლის მეშვეობით.
     */
    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody User user) {
        LoginResponse loginResponse = userService.loginUser(user);
        return ResponseEntity.ok(loginResponse);
    }
}
