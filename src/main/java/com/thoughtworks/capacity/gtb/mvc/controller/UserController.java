package com.thoughtworks.capacity.gtb.mvc.controller;


import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import com.thoughtworks.capacity.gtb.mvc.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void userRegister(@RequestBody @Valid User user) {
        log.info("user register , request: {}", user);
        userService.register(user);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User userLogin(@NotBlank(message = "用户名不为空")
                          @Size(min = 3, max = 10, message = "用户名不合法")
                          @Pattern(regexp = "\\w+$", message = "用户名不合法")
                          @RequestParam String username,

                          @NotBlank(message = "密码是不为空")
                          @Size(min = 5, max = 12, message = "密码不合法")
                          @RequestParam String password) {
        log.info("user login by username:{} and password: {}", username, password);
        return userService.login(username, password);
    }
}
