package com.example.week6_huynhngophuchau_19432051.Repositories;

import com.example.week6_huynhngophuchau_19432051.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
