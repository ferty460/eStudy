package com.example.estudy.web.controller;

import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
@Validated
public class LessonController {

    private final UserServiceImpl userService;

    @GetMapping("/type")
    public String lessonsType(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("followed_courses", user.getFollowedCourses());
        return "lesson_type";
    }

    @GetMapping("/create")
    public String createPage(@AuthenticationPrincipal UserDetails userDetails, Model model, @RequestParam("type") String type) {
        User user = userService.getByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("followed_courses", user.getFollowedCourses());
        if ("THEORETICAL".equals(type)) {
            return "theory_lesson";
        } else if ("PRACTICAL".equals(type)) {
            return "add_practice_lesson";
        } else {
            return "lesson_type";
        }
    }

}
