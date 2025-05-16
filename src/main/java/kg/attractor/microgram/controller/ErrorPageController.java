package kg.attractor.microgram.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class ErrorPageController {

    @GetMapping("/403")
    public String accessDeniedPage(HttpServletRequest request, Model model) {
        model.addAttribute("status", 403);
        model.addAttribute("reason", "Forbidden");
        model.addAttribute("details", request);
        return "errors/error";
    }
}