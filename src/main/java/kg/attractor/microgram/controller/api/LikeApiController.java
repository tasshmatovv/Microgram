package kg.attractor.microgram.controller.api;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.LikeService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeApiController {
    private final UserService userService;
    private final LikeService likeService;


    @PostMapping("/{postId}")
    public Map<String, Object> like(@PathVariable Integer postId) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto currentUser = userService.getUserByEmail(currentUserEmail);
        boolean liked = likeService.toggleLike(postId, currentUser.getId());

        int likesCount = likeService.countLikes(postId);

        Map<String, Object> response = new HashMap<>();
        response.put("likesCount", likesCount);
        response.put("liked", liked);

        return response;
    }


    @GetMapping("/status/{postId}")
    public ResponseEntity<?> getLikeStatus(@PathVariable Integer postId, Principal principal) {
        Map<String, Object> response = new HashMap<>();

        int likesCount = likeService.countLikes(postId);
        response.put("likesCount", likesCount);

        if (principal == null) {
            response.put("liked", false);
        } else {
            String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            UserDto currentUser = userService.getUserByEmail(currentUserEmail);
            boolean isLiked = likeService.isLikedByUser(postId, currentUser.getId());
            response.put("liked", isLiked);
        }

        return ResponseEntity.ok(response);
    }
}


