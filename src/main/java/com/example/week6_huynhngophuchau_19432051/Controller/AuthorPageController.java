package com.example.week6_huynhngophuchau_19432051.Controller;

import com.example.week6_huynhngophuchau_19432051.Service.PostService;
import com.example.week6_huynhngophuchau_19432051.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/authorPage")
public class AuthorPageController {
    private final PostService postService;
    private final UserService userService;

    public AuthorPageController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }
    @GetMapping("/{authorEmail}")
    public String showAuthorPage(@PathVariable String authorEmail, Model model) {
        model.addAttribute("authorPosts", postService.getPostsByAuthor(authorEmail));
        model.addAttribute("author", userService.getUserByEmail(authorEmail).orElse(null));
        return "authorPage";
    }
    @GetMapping("/myPage")
    public String showMyPage(Model model, Principal principal) {
        String userEmail = principal.getName();
        model.addAttribute("authorPosts", postService.getPostsByAuthor(userEmail));
        model.addAttribute("author", userService.getUserByEmail(userEmail).orElse(null));
        return "authorPage";
    }
}
