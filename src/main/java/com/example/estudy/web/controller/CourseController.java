package com.example.estudy.web.controller;

import com.example.estudy.domain.course.Course;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.CourseServiceImpl;
import com.example.estudy.service.impl.TagServiceImpl;
import com.example.estudy.service.impl.UserServiceImpl;
import com.example.estudy.web.dto.course.CourseDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
@Validated
public class CourseController {

    private final CourseServiceImpl courseService;
    private final UserServiceImpl userService;
    private final TagServiceImpl tagService;

    private final CourseMapper courseMapper;

    @GetMapping("/create")
    public String createPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("tags", tagService.getAll());
        return "add_course";
    }

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) CourseDto courseDto,
                         @AuthenticationPrincipal UserDetails userDetails,
                         @RequestParam("file") MultipartFile file) throws IOException {
        Course course = courseMapper.toEntity(courseDto);
        User user = userService.getByUsername(userDetails.getUsername());
        courseService.create(course, user.getId(), file);
        return "redirect:/profile";
    }

    @PutMapping
    public CourseDto update(@Validated(OnUpdate.class) CourseDto courseDto, Long courseId) {
        Course course = courseMapper.toEntity(courseDto);
        Course updatedCourse = courseService.update(course, courseId);
        return courseMapper.toDto(updatedCourse);
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        Course course = courseService.getById(id);
        model.addAttribute("course", course);
        return "course";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        courseService.delete(id);
        return "redirect:/profile";
    }

}
