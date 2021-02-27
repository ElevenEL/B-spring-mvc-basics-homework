package com.thoughtworks.capacity.gtb.mvc.service.impl;

import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import com.thoughtworks.capacity.gtb.mvc.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Map<String, User> users;

    @Override
    public void register(User user) {
        if (users.containsKey(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "用户已存在");
        }
        users.put(user.getUsername(), user);
    }

    @Override
    public User login(String username, String password) {
        User user = users.get(username);
        if (Objects.isNull(user) || !user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "用户名或密码错误");
        }
        return user;
    }
}
