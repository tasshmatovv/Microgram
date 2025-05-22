package kg.attractor.microgram.controller;

import kg.attractor.microgram.Util.FileUtil;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.PostService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final FileUtil fileUtil;
    private final PostService postService;

    @GetMapping
    public String getProfile(Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto currentUser = userService.getUserByEmail(currentUserEmail);
        model.addAttribute("user", currentUser);
        model.addAttribute("postCount", postService.getPostCount(currentUser.getId()));
        model.addAttribute("posts", postService.getPostsByUserId(currentUser.getId()));
        return "profile/profile";
    }

    @GetMapping("/avatars/{filename}")
    public ResponseEntity<?> getAvatar(@PathVariable String filename) {
        return fileUtil.getOutputFile(filename, "avatars");
    }

    @PostMapping("/editAvatar")
    public String editAvatar(@RequestParam(required = false) MultipartFile avatar,
                             @RequestParam String existingAvatar) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updateUserAvatar(currentEmail, avatar, existingAvatar);
        return "redirect:/profile";
    }

    @GetMapping("/image/{postImage}")
    public ResponseEntity<InputStreamResource> getPostImage(@PathVariable String postImage) {
        return postService.getPostImage(postImage);
    }
}
