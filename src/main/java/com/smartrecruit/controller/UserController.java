// UserController.java
package com.smartrecruit.controller;

import com.smartrecruit.entity.Resume;
import com.smartrecruit.entity.User;
import com.smartrecruit.service.ResumeService;
import com.smartrecruit.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ResumeService resumeService;

    public UserController(UserService userService, ResumeService resumeService) {
        this.userService = userService;
        this.resumeService = resumeService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElseThrow();
        model.addAttribute("user", user);

        Resume resume = resumeService.findByUserId(user.getId());
        model.addAttribute("hasResume", resume != null);

        return "user/dashboard";
    }

    @GetMapping("/resume")
    public String resumeForm(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElseThrow();
    
        Resume resume = resumeService.findByUserId(user.getId());
        if (resume == null) {
            resume = new Resume();
            resume.setUser(user);
        }
    
        model.addAttribute("resume", resume);
        return "user/resume";
    }

    @PostMapping("/resume")
    public String saveResume(@ModelAttribute Resume resume, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElseThrow();

        resume.setUser(user);
        resumeService.save(resume);

        return "redirect:/user/dashboard";
    }
}
