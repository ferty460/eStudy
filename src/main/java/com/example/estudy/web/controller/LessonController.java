package com.example.estudy.web.controller;

import com.example.estudy.domain.lesson.Lesson;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.LessonServiceImpl;
import com.example.estudy.service.impl.UserServiceImpl;
import com.example.estudy.web.dto.lesson.LessonDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
@Validated
public class LessonController {

    private final UserServiceImpl userService;
    private final LessonServiceImpl lessonService;

    private final LessonMapper lessonMapper;

    @GetMapping
    public String getById(@RequestParam("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getByUsername(userDetails.getUsername());
        Lesson lesson = lessonService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("followed_courses", user.getFollowedCourses());
        model.addAttribute("lesson", lesson);
        model.addAttribute("isCourseOwner",
                userService.isCourseOwner(user.getId(), lesson.getModule().getCourse().getId()));
        return "lesson";
    }

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) LessonDto lessonDto, Long moduleId) {
        Lesson lesson = lessonMapper.toEntity(lessonDto);
        lessonService.create(lesson, moduleId);
        return "redirect:/modules?id=" + lesson.getModule().getId();
    }

    @PostMapping("/edit")
    public String update(@Validated(OnUpdate.class) LessonDto lessonDto) {
        Lesson lesson = lessonMapper.toEntity(lessonDto);
        lessonService.update(lesson, lesson.getId());
        return "redirect:/lessons?id=" + lesson.getId();
    }

    @PostMapping("/delete")
    public String delete(Long id) {
        Long moduleId = lessonService.getById(id).getModule().getId();
        lessonService.delete(id);
        return "redirect:/modules?id=" + moduleId;
    }

}
