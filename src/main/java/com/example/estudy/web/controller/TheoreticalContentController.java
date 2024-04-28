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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@theoreticalContentServiceImpl.getById(#id).lesson.module.course.author.id == authentication.principal.id")
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

    @PostMapping("/create")@PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@lessonServiceImpl.getById(#lessonId).module.course.author.id == authentication.principal.id")
    public String create(@Validated(OnCreate.class) TheoreticalContentDto contentDto, Long lessonId) {
        TheoreticalContent content = contentMapper.toEntity(contentDto);
        contentService.create(content, lessonId);
        return "redirect:/theoretical?id=" + content.getId();
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@theoreticalContentServiceImpl.getById(#contentDto.id).lesson.module.course.author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) TheoreticalContentDto contentDto) {
        TheoreticalContent content = contentMapper.toEntity(contentDto);
        TheoreticalContent editedContent = contentService.update(content, content.getId());
        return "redirect:/theoretical?id=" + editedContent.getId();
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@theoreticalContentServiceImpl.getById(#contentDto.id).lesson.module.course.author.id == authentication.principal.id")
    public String edit(@Validated(OnUpdate.class) TheoreticalContentDto contentDto) {
        TheoreticalContent content = contentMapper.toEntity(contentDto);
        TheoreticalContent editedContent = contentService.update(content, content.getId());
        return "redirect:/lessons?id=" + editedContent.getLesson().getId();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@theoreticalContentServiceImpl.getById(#id).lesson.module.course.author.id == authentication.principal.id")
    public String delete(Long id) {
        Long lessonId = contentService.getById(id).getLesson().getId();
        contentService.delete(id);
        return "redirect:/lessons?id=" + lessonId;
    }

}
