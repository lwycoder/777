// AuthController.java
package com.smartrecruit.controller;

import com.smartrecruit.entity.User;
import com.smartrecruit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "用户名已存在");
            return "auth/register";
        }
        userService.registerUser(user);
        return "redirect:/user/dashboard";
    }
}
