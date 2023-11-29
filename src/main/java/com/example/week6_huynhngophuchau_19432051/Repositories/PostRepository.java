package com.example.week6_huynhngophuchau_19432051.Repositories;

import com.example.week6_huynhngophuchau_19432051.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByAuthor_Email(String email);
}
