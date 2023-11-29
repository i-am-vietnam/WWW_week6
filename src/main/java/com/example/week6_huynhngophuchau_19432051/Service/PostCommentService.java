package com.example.week6_huynhngophuchau_19432051.Service;

import com.example.week6_huynhngophuchau_19432051.Model.PostComment;

import java.util.List;
import java.util.Optional;

public interface PostCommentService {
    Optional<PostComment> getPostCommentById(Long postCommentId);
    List<PostComment> getAllPostComments();
    List<PostComment> findByPostIdWithChildComments(Long postId, Long parrentPostCommentId);
    PostComment savePostComment(PostComment postComment);
    void deletePostComment(Long postCommentId);


}

