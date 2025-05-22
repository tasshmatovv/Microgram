package kg.attractor.microgram.controller;

import jakarta.validation.Valid;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.exceptions.UserAlreadyExistsException;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("userRegisterDto", new UserDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userRegisterDto") UserDto userDto, BindingResult bindingResult, @RequestParam("avatar") MultipartFile avatar, Model model) {

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        try {
            userService.registerUser(userDto, avatar.getOriginalFilename());
            return "redirect:/auth/login";
        } catch (UserAlreadyExistsException e) {
            if (e.getMessage().contains("Логина")) {
                model.addAttribute("nickError", e.getMessage());
            } else {
                model.addAttribute("emailError", e.getMessage());
            }
            return "auth/register";
        }
    }

    @GetMapping("/login")
    public String getLogin() {
        return "auth/login";
    }

}