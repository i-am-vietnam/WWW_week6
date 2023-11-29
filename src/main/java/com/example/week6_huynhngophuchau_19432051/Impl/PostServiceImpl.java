package com.example.week6_huynhngophuchau_19432051.Impl;

import com.example.week6_huynhngophuchau_19432051.Model.Post;
import com.example.week6_huynhngophuchau_19432051.Model.PostComment;
import com.example.week6_huynhngophuchau_19432051.Repositories.PostCommentRepository;
import com.example.week6_huynhngophuchau_19432051.Repositories.PostRepository;
import com.example.week6_huynhngophuchau_19432051.Service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostCommentRepository postCommentRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> getAllPosts(int page, int size) {
        Page<Post> postPage = postRepository.findAll(PageRequest.of(page, size));
        return postPage.getContent();
    }

    @Override
    public List<PostComment> getCommentsByPostId(Long postId) {
        Long parentId = null;
        return postCommentRepository.findPostCommentByPost_IdAndParentComment_Id(postId, parentId);
    }


    @Override
    public PostComment addComment(Post post, PostComment postComment) {
        postComment.setPost(post);
        return postCommentRepository.save(postComment);
    }

    @Override
    public Optional<Post> updatePost(Long postId, Post post) {
        return postRepository.findById(postId).map(existingPost -> {
            BeanUtils.copyProperties(post, existingPost, "id", "author", "createdAt", "publishedAt");
            Post savedPost = postRepository.save(existingPost);
            return Optional.of(savedPost);
        }).orElse(Optional.empty());
    }

    @Override
    public List<Post> getPostsByAuthor(String authorEmail) {
        return postRepository.findAllByAuthor_Email(authorEmail);
    }
}
