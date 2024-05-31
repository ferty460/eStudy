package com.example.estudy.web.controller;

import com.example.estudy.domain.course.Availability;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.course.CourseServiceImpl;
import com.example.estudy.service.impl.course.LessonServiceImpl;
import com.example.estudy.service.impl.user.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserServiceImpl userService;
    private final CourseServiceImpl courseService;
    private final LessonServiceImpl lessonService;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpSession session) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
        }
        model.addAttribute("courses", courseService.getTop5ByRating(Availability.PUBLIC));
        model.addAttribute("theme", tempToggleTheme(session));

        return "main";
    }

    @GetMapping("/about")
    public String about(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpSession session) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
        }
        model.addAttribute("coursesCount", courseService.count());
        model.addAttribute("usersCount", userService.count());
        model.addAttribute("lessonsCount", lessonService.count());
        model.addAttribute("theme", tempToggleTheme(session));

        return "about";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpSession session) {
        User user = userService.getByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("my_courses", courseService.getAllByUserId(user.getId()));
        model.addAttribute("followed_courses", user.getFollowedCourses());
        model.addAttribute("favorites_courses", user.getFavoriteCourses());
        model.addAttribute("theme", tempToggleTheme(session));
        return "profile";
    }

    @PostMapping("/toggle-theme")
    public String toggleTheme(HttpSession session) {
        String currentTheme = (String) session.getAttribute("theme");

        String newTheme = (currentTheme == null || currentTheme.equals("dark")) ? "light" : "dark";
        session.setAttribute("theme", newTheme);

        return "redirect:/";
    }

    public String tempToggleTheme(HttpSession session) {
        String theme = (String) session.getAttribute("theme");
        if (theme == null) {
            theme = "light";
            session.setAttribute("theme", theme);
        }
        return theme;
    }

}
