package com.example.estudy.web.controller;

import com.example.estudy.domain.course.Availability;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.course.CourseServiceImpl;
import com.example.estudy.service.impl.user.UserServiceImpl;
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
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
        }
        model.addAttribute("courses", courseService.getTop5ByRating(Availability.PUBLIC));

        return "main";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("my_courses", courseService.getAllByUserId(user.getId()));
        model.addAttribute("followed_courses", user.getFollowedCourses());
        model.addAttribute("favorites_courses", user.getFavoriteCourses());
        return "profile";
    }

}
