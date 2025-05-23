package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.PostDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.PostService;
import kg.attractor.microgram.service.SubscriptionsService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final SubscriptionsService subscriptionsService;
    private final PostService postService;

    @GetMapping("/")
    public String homePage(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            model.addAttribute("feed", subscriptionsService.getPublicFeed());
            model.addAttribute("isGuest", true);
        } else {
            UserDto currentUser = userService.getUserByEmail(authentication.getName());
            List<PostDto> personalizedFeed = subscriptionsService.getPersonalizedFeed(currentUser.getId());

            if (personalizedFeed.isEmpty()) {
                List<UserDto> suggestedUsers = userService.getSuggestedUsersForSubscription(currentUser.getId());
                model.addAttribute("suggestedUsers", suggestedUsers);
            } else {
                model.addAttribute("feed", personalizedFeed);
            }

            model.addAttribute("currentUser", currentUser);
            model.addAttribute("isGuest", false);
        }

        return "main/main";
    }


    @GetMapping("search")
    public String showExplorePage(Model model) {
        model.addAttribute("post", postService.getAllPosts());

        return "main/explore";
    }

}
