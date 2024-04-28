package com.example.estudy.web.controller;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.GapsTask;
import com.example.estudy.domain.lesson.content.practical.SortingTask;
import com.example.estudy.domain.lesson.content.practical.Test;
import com.example.estudy.domain.lesson.content.practical.TextTask;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.PracticalContentServiceImpl;
import com.example.estudy.service.impl.TextTaskServiceImpl;
import com.example.estudy.service.impl.UserServiceImpl;
import com.example.estudy.web.dto.lesson.PracticalContentDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.PracticalContentMapper;
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
@RequestMapping("/practical")
@RequiredArgsConstructor
@Validated
public class PracticalContentController {

    private final PracticalContentServiceImpl contentService;
    private final UserServiceImpl userService;
    private final TextTaskServiceImpl textTaskService;

    private final PracticalContentMapper contentMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@practicalContentServiceImpl.getById(#id).lesson.module.course.author.id == authentication.principal.id")
    public String getById(@RequestParam("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getByUsername(userDetails.getUsername());
        PracticalContent content = contentService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("followed_courses", user.getFollowedCourses());
        model.addAttribute("isCourseOwner",
                userService.isCourseOwner(user.getId(), content.getLesson().getModule().getCourse().getId()));

        switch (content.getPracticalType()) {
            case "test" -> {
                model.addAttribute("content", content.getTest());
                return "test_task";
            }
            case "gaps" -> {
                model.addAttribute("content", content.getGapsTask());
                return "gaps_task";
            }
            case "text" -> {
                model.addAttribute("content", content.getTextTask());
                return "text_task";
            }
            case "sort" -> {
                model.addAttribute("content", content.getSortingTask());
                return "sort_task";
            }
            default -> {
                return "redirect:/lessons?id=" + content.getLesson().getId();
            }
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@lessonServiceImpl.getById(#lessonId).module.course.author.id == authentication.principal.id")
    public String create(@Validated(OnCreate.class) PracticalContentDto contentDto, Long lessonId) {
        PracticalContent content = contentMapper.toEntity(contentDto);

        contentService.create(content, lessonId);

        /* TODO: переделать */
        switch (content.getPracticalType()) {
            case "test" -> {
                Test test = new Test();
                content.setTest(test);
                test.setTitle(content.getTitle());
                test.setDescription(content.getDescription());
                test.setPracticalContent(content);
            }
            case "gaps" -> {
                GapsTask gapsTask = new GapsTask();
                content.setGapsTask(gapsTask);
                gapsTask.setTitle(content.getTitle());
                gapsTask.setDescription(content.getDescription());
                gapsTask.setPracticalContent(content);
            }
            case "text" -> textTaskService.create(content);
            case "sort" -> {
                SortingTask sortingTask = new SortingTask();
                content.setSortingTask(sortingTask);
                sortingTask.setTitle(content.getTitle());
                sortingTask.setDescription(content.getDescription());
                sortingTask.setPracticalContent(content);
            }
        }

        return "redirect:/practical?id=" + content.getId();
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@practicalContentServiceImpl.getById(#contentDto.id).lesson.module.course.author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) PracticalContentDto contentDto) {
        PracticalContent content = contentMapper.toEntity(contentDto);
        PracticalContent editedContent = contentService.update(content, content.getId());

        /* TODO: переделать */
        switch (editedContent.getPracticalType()) {
            case "test" -> {
                System.out.println("later");
            }
            case "gaps" -> {
                System.out.println("maybe later");
            }
            case "text" -> {
                TextTask editedTextTask = textTaskService.getById(editedContent.getTextTask().getId());
                editedTextTask.setTitle(editedContent.getTitle());
                editedTextTask.setDescription(editedContent.getDescription());
                textTaskService.update(editedTextTask, editedContent.getTextTask().getId());
            }
            case "sort" -> {
                System.out.println("definitely later");
            }
        }

        return "redirect:/practical?id=" + editedContent.getId();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@practicalContentServiceImpl.getById(#id).lesson.module.course.author.id == authentication.principal.id")
    public String delete(Long id) {
        Long lessonId = contentService.getById(id).getLesson().getId();
        contentService.delete(id);
        return "redirect:/lessons?id=" + lessonId;
    }

}
