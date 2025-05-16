package kg.attractor.microgram.controller;

import kg.attractor.microgram.Util.FileUtil;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final FileUtil fileUtil;

    @GetMapping
    public String getProfile(Model model) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto currentUser = userService.getUserByEmail(currentUserEmail);
        model.addAttribute("user", currentUser);

        return "profile/profile";
    }

    @GetMapping("/avatars/{filename}")
    public ResponseEntity<?> getAvatar(@PathVariable String filename) {
        Path avatarPath = Paths.get("data/avatars", filename);

        if (Files.exists(avatarPath)) {
            return fileUtil.getOutputFile("avatars/" + filename, MediaType.IMAGE_JPEG);
        }

        ClassPathResource defaultImage = new ClassPathResource("static/images/" + filename);
        try {
            byte[] image = defaultImage.getInputStream().readAllBytes();
            Resource resource = new ByteArrayResource(image);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
