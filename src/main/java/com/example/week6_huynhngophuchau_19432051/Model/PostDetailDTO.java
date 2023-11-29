package com.example.week6_huynhngophuchau_19432051.Model;

import java.util.List;

public class PostDetailDTO {
    private Post post;
    private User user;
    private List<PostComment> comments;
    private PostComment newComment;
    public PostDetailDTO(Post post, User user, List<PostComment> comments, PostComment newComment) {
        this.post = post;
        this.user = user;
        this.comments = comments;
        this.newComment = newComment;
    }
    public Post getPost() {
        return post;
    }
    public User getUser() {
        return user;
    }
    public List<PostComment> getComments() {
        return comments;
    }
    public PostComment getNewComment() {
        return newComment;
    }
}
