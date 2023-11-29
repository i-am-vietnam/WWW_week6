package com.example.week6_huynhngophuchau_19432051.Service;

import com.example.week6_huynhngophuchau_19432051.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long userId);
    List<User> getAllUsers();
    User saveUser(User user);
    Optional<User> getUserByEmail(String email);
    void deleteUser(Long userId);
}
