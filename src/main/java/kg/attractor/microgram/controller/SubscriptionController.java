package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.SubscriptionsService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionsService subscriptionService;
    private final UserService userService;

    @PostMapping("/follow/{followedId}")
    public String followUser(@PathVariable Integer followedId, Authentication authentication) {
        UserDto currentUser = userService.getUserByEmail(authentication.getName());
        subscriptionService.follow(currentUser.getId(), followedId);
        return "redirect:/profile";
    }

    @PostMapping("/unfollow/{followedId}")
    public String unfollowUser(@PathVariable Integer followedId, Authentication authentication) {
        UserDto currentUser = userService.getUserByEmail(authentication.getName());
        subscriptionService.unfollow(currentUser.getId(), followedId);
        return "redirect:/profile";
    }
}

