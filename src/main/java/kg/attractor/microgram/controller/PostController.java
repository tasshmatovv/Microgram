package kg.attractor.microgram.controller;

import jakarta.validation.Valid;
import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.PostDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.CommentService;
import kg.attractor.microgram.service.PostService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/create")
    public String getCreatePostPage(Model model) {
        model.addAttribute("post", new PostDto());
        return "post/createPost";
    }

    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult bindingResult,
                             Authentication authentication) {

        MultipartFile imageFile = postDto.getImageUrl();
        if (imageFile == null || imageFile.isEmpty()) {
            if (!bindingResult.hasFieldErrors("imageUrl")) {
                bindingResult.addError(new FieldError("post", "imageUrl", "Вы не можете опубликовать пустой пост"));
            }
        }

        if (bindingResult.hasErrors()) {
            return "post/createPost";
        }

        postService.createPost(postDto, authentication);
        return "redirect:/profile";
    }

    @GetMapping("/details/{id}")
    public String getPostDetailsPage(@PathVariable Integer id, Model model, Authentication authentication) {

        UserDto currentUser = userService.getUserByEmail(authentication.getName());

        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("comments", commentService.getCommentsByPostId(id));
        model.addAttribute("newComment", new CommentDto());
        model.addAttribute("currentUser", currentUser);

        return "post/postDetails";
    }


    @PostMapping("/{postId}/comment")
    public String addComment(@PathVariable Integer postId,
                             @ModelAttribute("newComment") @Valid CommentDto commentDto,
                             BindingResult bindingResult,
                             Authentication authentication,
                             Model model) {

        if (bindingResult.hasErrors()) {
            PostDto post = postService.getPostById(postId);
            model.addAttribute("post", post);
            model.addAttribute("comments", commentService.getCommentsByPostId(postId));
            return "post/postDetails";
        }

        commentDto.setPostId(postId);
        UserDto currentUser = userService.getUserByEmail(authentication.getName());
        commentDto.setUser(currentUser);

        commentService.addComment(commentDto);
        postService.updateCommentCount(postId);

        return "redirect:/post/details/" + postId;
    }


    @PostMapping("/{postId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Integer postId,
                                @PathVariable Integer commentId,
                                Authentication authentication) {
        UserDto currentUser = userService.getUserByEmail(authentication.getName());

        try {
            commentService.deleteCommentIfPostOwnedBy(commentId, postId, currentUser);
        } catch (RuntimeException e) {
            try {
                commentService.deleteOwnComment(commentId, currentUser.getId());
            } catch (RuntimeException ex) {
                return "redirect:/post/details/" + postId;
            }
        }
        return "redirect:/post/details/" + postId;
    }



}
