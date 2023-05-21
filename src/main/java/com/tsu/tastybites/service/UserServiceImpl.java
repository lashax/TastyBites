package com.tsu.tastybites.service;

import com.tsu.tastybites.entity.User;
import com.tsu.tastybites.model.LoginResponse;
import com.tsu.tastybites.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);

        return savedUser.getEmail();
    }

    @Override
    public LoginResponse loginUser(User user) {
        User user1 = userRepository.findByEmail(user.getEmail());

        if (user1 == null) {
            return new LoginResponse("Email not found", false);
        }

        String password = user.getPassword();
        String encodedPassword = user1.getPassword();
        boolean isPasswordCorrect = passwordEncoder.matches(password, encodedPassword);

        if (!isPasswordCorrect) {
            return new LoginResponse("Password is incorrect", false);
        } else {
            return new LoginResponse("Login success", true);
        }
    }
}
