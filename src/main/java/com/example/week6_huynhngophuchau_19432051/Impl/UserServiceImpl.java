package com.example.week6_huynhngophuchau_19432051.Impl;

import com.example.week6_huynhngophuchau_19432051.Model.User;
import com.example.week6_huynhngophuchau_19432051.Repositories.UserRepository;
import com.example.week6_huynhngophuchau_19432051.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
