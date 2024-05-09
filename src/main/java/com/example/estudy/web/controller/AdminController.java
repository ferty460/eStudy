package com.example.estudy.web.controller;

import com.example.estudy.domain.user.User;
import com.example.estudy.service.dao.course.TagService;
import com.example.estudy.service.dao.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final UserService userService;
    private final TagService tagService;

    @GetMapping
    public String adminPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("followed_courses", user.getFollowedCourses());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("tags", tagService.getAll());
        return "admin";
    }

}
