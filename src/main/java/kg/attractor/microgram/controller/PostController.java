package kg.attractor.microgram.controller;

import jakarta.validation.Valid;
import kg.attractor.microgram.dto.PostDto;
import kg.attractor.microgram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/create")
    public String getCreatePostPage(Model model) {
        model.addAttribute("post", new PostDto());
        return "post/createPost";
    }

    @PostMapping("/create")
    public String createPost(@Valid PostDto postDto,Authentication authentication) {
        postService.createPost(postDto, authentication);
        return "redirect:/profile";
    }

    @GetMapping("/details/{id}")
    public String getPostDetailsPage(@PathVariable Integer id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "post/postDetails";
    }
}
