package com.example.week6_huynhngophuchau_19432051.Controller;

import com.example.week6_huynhngophuchau_19432051.Model.Post;
import com.example.week6_huynhngophuchau_19432051.Model.User;
import com.example.week6_huynhngophuchau_19432051.Repositories.UserRepository;
import com.example.week6_huynhngophuchau_19432051.Service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final PostService postService;
    private final UserRepository userRepository;
    public HomeController(PostService postService, UserRepository userRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
    }
    @GetMapping
    public String home(Pageable pageable, Model model) {
        Page<Post> postPage = postService.getAllPosts(pageable);
        model.addAttribute("postPage", postPage);
        model.addAttribute("newPost", new Post());
        return "authorPage";
    }
    @PostMapping("/addPost")
    public String addPost(@ModelAttribute("newPost") Post newPost, Principal principal) {
        String userEmail = principal.getName();
        User currentUser = userRepository.findByEmail(userEmail).orElseThrow();
        newPost.setAuthor(currentUser);
        newPost.setCreatedAt(new Date(System.currentTimeMillis()));
        postService.savePost(newPost);
        return "redirect:/home";
    }
    @GetMapping("/page/{pageNumber}")
    public String getPostsPaging(@PathVariable int pageNumber, Pageable pageable, Model model) {
        Pageable updatedPageable = PageRequest.of(pageNumber, pageable.getPageSize(), pageable.getSort());
        Page<Post> postPage = postService.getAllPosts(updatedPageable);
        model.addAttribute("newPost", new Post());
        model.addAttribute("postPage", postPage);
        return "authorPage";
    }

}
