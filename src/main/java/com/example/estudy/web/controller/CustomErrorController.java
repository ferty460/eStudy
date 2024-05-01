package com.example.estudy.web.controller;

import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class CustomErrorController {

    private final UserServiceImpl userService;

    @RequestMapping("/oops")
    public String handleError(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
        }

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403";
            } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "error/400";
            }
        }
        return "error/undefined";
    }

}
