package com.example.estudy.web.controller;

import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.CourseServiceImpl;
import com.example.estudy.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserServiceImpl userService;
    private final CourseServiceImpl courseService;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("my_courses", courseService.getAllByUserId(user.getId()));
            model.addAttribute("user", user);
        }

        return "main";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("my_courses", courseService.getAllByUserId(user.getId()));
        return "profile";
    }

}
