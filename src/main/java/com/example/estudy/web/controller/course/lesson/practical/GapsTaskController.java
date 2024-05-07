package com.example.estudy.web.controller.course.lesson.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.GapsTask;
import com.example.estudy.service.impl.course.content.practical.GapsTaskServiceImpl;
import com.example.estudy.service.impl.course.content.PracticalContentServiceImpl;
import com.example.estudy.web.dto.lesson.GapsTaskDto;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.GapsTaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practical/gaps")
@RequiredArgsConstructor
@Validated
public class GapsTaskController {

    private final GapsTaskServiceImpl taskService;
    private final PracticalContentServiceImpl practicalContentService;

    private final GapsTaskMapper taskMapper;

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@gapsTaskServiceImpl.getById(#taskDto.id).practicalContent.lesson.module.course.author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) GapsTaskDto taskDto) {
        GapsTask task = taskMapper.toEntity(taskDto);
        GapsTask editedTask = taskService.update(task, task.getId());

        PracticalContent content = new PracticalContent();
        content.setTitle(task.getTitle());
        content.setDescription(task.getDescription());
        practicalContentService.update(content, editedTask.getPracticalContent().getId());

        return "redirect:/practical?id=" + editedTask.getPracticalContent().getId();
    }

}
