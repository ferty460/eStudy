package com.example.estudy.web.controller.course.lesson.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.TextTask;
import com.example.estudy.service.impl.PracticalContentServiceImpl;
import com.example.estudy.service.impl.TextTaskServiceImpl;
import com.example.estudy.web.dto.lesson.TextTaskDto;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.TextTaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practical/text")
@RequiredArgsConstructor
@Validated
public class TextTaskController {

    private final TextTaskServiceImpl textTaskService;
    private final PracticalContentServiceImpl practicalContentService;

    private final TextTaskMapper textTaskMapper;

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@textTaskServiceImpl.getById(#textTaskDto.id).practicalContent.lesson.module.course.author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) TextTaskDto textTaskDto) {
        TextTask textTask = textTaskMapper.toEntity(textTaskDto);
        TextTask editedTextTask = textTaskService.update(textTask, textTask.getId());

        PracticalContent content = new PracticalContent();
        content.setTitle(textTaskDto.getTitle());
        content.setDescription(textTask.getDescription());
        practicalContentService.update(content, editedTextTask.getPracticalContent().getId());

        return "redirect:/practical?id=" + editedTextTask.getPracticalContent().getId();
    }

}
