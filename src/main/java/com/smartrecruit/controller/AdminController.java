// AdminController.java
package com.smartrecruit.controller;

import com.smartrecruit.entity.JobAd;
import com.smartrecruit.entity.User;
import com.smartrecruit.service.JobAdService;
import com.smartrecruit.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final JobAdService jobAdService;
    private final UserService userService;

    public AdminController(JobAdService jobAdService, UserService userService) {
        this.jobAdService = jobAdService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        User admin = userService.findByUsername(username).orElseThrow();

        model.addAttribute("jobCount", jobAdService.countByAdmin(admin));
        model.addAttribute("activeJobCount", jobAdService.countActiveJobsByAdmin(admin));
        model.addAttribute("applicationCount", jobAdService.countApplicationsByAdmin(admin));

        return "admin/dashboard";
    }

    @GetMapping("/jobs")
    public String manageJobs(Authentication authentication, Model model) {
        String username = authentication.getName();
        User admin = userService.findByUsername(username).orElseThrow();

        model.addAttribute("jobAds", jobAdService.findAllByAdmin(admin));
        return "admin/jobs";
    }

    @GetMapping("/job/create")
    public String createJobForm(Model model) {
        model.addAttribute("jobAd", new JobAd());
        return "admin/job_form";
    }

    @PostMapping("/job/save")
    public String saveJob(@ModelAttribute JobAd jobAd, Authentication authentication) {
        String username = authentication.getName();
        User admin = userService.findByUsername(username).orElseThrow();

        jobAd.setAdmin(admin);
        jobAdService.save(jobAd);
        return "redirect:/admin/jobs";
    }
}