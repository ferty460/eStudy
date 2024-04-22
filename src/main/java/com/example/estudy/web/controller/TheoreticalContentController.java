package com.example.estudy.web.controller;

import com.example.estudy.domain.lesson.content.TheoreticalContent;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.TheoreticalContentServiceImpl;
import com.example.estudy.service.impl.UserServiceImpl;
import com.example.estudy.web.dto.lesson.TheoreticalContentDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.TheoreticalContentMapper;
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
@RequestMapping("/theoretical")
@RequiredArgsConstructor
@Validated
public class TheoreticalContentController {

    private final TheoreticalContentServiceImpl contentService;
    private final UserServiceImpl userService;

    private final TheoreticalContentMapper contentMapper;

    @GetMapping
    public String getById(@RequestParam("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getByUsername(userDetails.getUsername());
        TheoreticalContent content = contentService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("followed_courses", user.getFollowedCourses());
        model.addAttribute("isCourseOwner",
                userService.isCourseOwner(user.getId(), content.getLesson().getModule().getCourse().getId()));
        model.addAttribute("content", content);
        return "theory_lesson";
    }

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) TheoreticalContentDto contentDto, Long lessonId) {
        TheoreticalContent content = contentMapper.toEntity(contentDto);
        contentService.create(content, lessonId);
        return "redirect:/theoretical?id=" + content.getId();
    }

    @PostMapping("/update")
    public String update(@Validated(OnUpdate.class) TheoreticalContentDto contentDto) {
        TheoreticalContent editedContent = contentMapper.toEntity(contentDto);
        contentService.update(editedContent, editedContent.getId());
        return "redirect:/theoretical?id=" + editedContent.getId();
    }

}
