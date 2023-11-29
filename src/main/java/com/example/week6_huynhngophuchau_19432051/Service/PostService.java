package com.example.week6_huynhngophuchau_19432051.Service;

import com.example.week6_huynhngophuchau_19432051.Model.Post;
import com.example.week6_huynhngophuchau_19432051.Model.PostComment;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long postId);
    List<Post> getAllPosts(int page, int size);
    public PostComment addComment(Post post, PostComment postComment);
    Optional<Post> updatePost(Long postId, Post post);
    List<PostComment> getCommentsByPostId(Long postId);
    List<Post> getPostsByAuthor(String authorEmail);
    Post savePost(Post post);
    void deletePost(Long postId);
}
