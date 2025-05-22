package kg.attractor.microgram.controller;

import kg.attractor.microgram.Util.FileUtil;
import kg.attractor.microgram.dto.PostDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.PostService;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.service.impl.SubscriptionsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final FileUtil fileUtil;
    private final PostService postService;
    private final SubscriptionsServiceImpl subscriptionService;

    @GetMapping
    public String getProfile(Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto currentUser = userService.getUserByEmail(currentUserEmail);
        model.addAttribute("user", currentUser);
        model.addAttribute("postCount", postService.getPostCount(currentUser.getId()));
        model.addAttribute("posts", postService.getPostsByUserId(currentUser.getId()));
        model.addAttribute("followerCount", subscriptionService.countByFollowedId(currentUser.getId()));
        model.addAttribute("followedCount", subscriptionService.countByFollowerId(currentUser.getId()));
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

    @GetMapping("/{id}")
    public String getUserProfile(@PathVariable Integer id, Model model, Authentication authentication) {
        UserDto profileUser = userService.getUserById(id);
        model.addAttribute("user", profileUser);

        Integer postCount = postService.getPostCount(id);
        model.addAttribute("postCount", postCount);

        List<PostDto> posts = postService.getPostsByUserId(id);
        model.addAttribute("posts", posts);

        model.addAttribute("followerCount", subscriptionService.countByFollowedId(id));
        model.addAttribute("followedCount", subscriptionService.countByFollowerId(id));


        UserDto currentUser = null;
        boolean isOwner = false;
        boolean isFollowing = false;

        if (authentication != null && authentication.isAuthenticated()) {
            currentUser = userService.getUserByEmail(authentication.getName());
            isOwner = currentUser.getId().equals(id);
            isFollowing = subscriptionService.isFollowing(currentUser.getId(), id);
        }

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("isFollowing", isFollowing);

        return "profile/anotherUserProfile";
    }

}
