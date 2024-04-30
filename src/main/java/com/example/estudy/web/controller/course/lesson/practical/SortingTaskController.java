package com.example.estudy.web.controller.course.lesson.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.SortingTask;
import com.example.estudy.service.impl.PracticalContentServiceImpl;
import com.example.estudy.service.impl.SortingTaskServiceImpl;
import com.example.estudy.web.dto.lesson.SortingTaskDto;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.SortingTaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practical/sort")
@RequiredArgsConstructor
@Validated
public class SortingTaskController {

    private final SortingTaskServiceImpl taskService;
    private final PracticalContentServiceImpl practicalContentService;

    private final SortingTaskMapper taskMapper;

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@sortingTaskServiceImpl.getById(#taskDto.id).practicalContent.lesson.module.course.author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) SortingTaskDto taskDto) {
        SortingTask task = taskMapper.toEntity(taskDto);
        SortingTask editedTask = taskService.update(task, task.getId());

        PracticalContent content = new PracticalContent();
        content.setTitle(task.getTitle());
        content.setDescription(task.getDescription());
        practicalContentService.update(content, editedTask.getPracticalContent().getId());

        return "redirect:/practical?id=" + editedTask.getPracticalContent().getId();
    }

}
