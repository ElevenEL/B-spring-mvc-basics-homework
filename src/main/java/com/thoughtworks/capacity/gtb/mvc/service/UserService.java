package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.vo.User;

public interface UserService {

    void register(User user);

    User login(String username, String password);
}
