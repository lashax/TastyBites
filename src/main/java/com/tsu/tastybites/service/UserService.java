package com.tsu.tastybites.service;

import com.tsu.tastybites.entity.User;
import com.tsu.tastybites.model.LoginResponse;

public interface UserService {

    String addUser(User user);

    LoginResponse loginUser(User user);
}
