package com.example.week6_huynhngophuchau_19432051.Controller;

import com.example.week6_huynhngophuchau_19432051.Model.Post;
import com.example.week6_huynhngophuchau_19432051.Model.PostComment;
import com.example.week6_huynhngophuchau_19432051.Model.PostDetailDTO;
import com.example.week6_huynhngophuchau_19432051.Model.User;
import com.example.week6_huynhngophuchau_19432051.Service.PostCommentService;
import com.example.week6_huynhngophuchau_19432051.Service.PostService;
import com.example.week6_huynhngophuchau_19432051.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class PostDetailController {

    private final PostService postService;
    private final PostCommentService postCommentService;
    private final UserService userService;

    @Autowired
    public PostDetailController(PostService postService, PostCommentService postCommentService, UserService userService) {
        this.postService = postService;
        this.postCommentService = postCommentService;
        this.userService = userService;
    }

    @GetMapping("/post/{postId}")
    public String postDetail(@PathVariable Long postId, Model model, Principal principal) {
        Optional<Post> postOptional = postService.getPostById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            User user = userService.getUserByEmail(principal.getName()).orElseThrow();

            List<PostComment> postComments = postService.getCommentsByPostId(postId);
            postComments.forEach(postComment -> {
                List<PostComment> childPostComments = postCommentService.findByPostIdWithChildComments(postId, postComment.getId());
                postComment.setChildComments(childPostComments);
            });

            model.addAttribute("postDetail", new PostDetailDTO(post, user, postComments, new PostComment()));
            return "postDetailPage";
        } else {
            // Handle case where post is not found
            return "error";
        }
    }

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute("postDetail") PostDetailDTO postDetail) {
        Long postId = postDetail.getPost().getId();
        Long userId = postDetail.getUser().getId();
        Long parentId = postDetail.getPost().getId();

        Post post = Post.builder().id(postId).build();
        User user = User.builder().id(userId).build();

        PostComment newComment = new PostComment();
        newComment.setContent(postDetail.getNewComment().getContent());
        newComment.setPost(post);
        newComment.setUser(user);

        if (parentId != null) {
            PostComment parentComment = PostComment.builder().id(parentId).build();
            newComment.setParentComment(parentComment);
        }

        postCommentService.savePostComment(newComment);
        return "redirect:/post/" + postId;
    }

}
