package com.thoughtworks.capacity.gtb.mvc.controller;


import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import com.thoughtworks.capacity.gtb.mvc.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void userRegister(@RequestBody @Valid User user) {
        userService.register(user);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User userLogin(@RequestParam @Valid String username, @RequestParam @Valid String password) {
        return userService.login(username, password);
    }
}
