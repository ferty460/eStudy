package com.example.estudy.web.controller.course.lesson.practical;

import com.example.estudy.domain.lesson.content.practical.SortingTaskElement;
import com.example.estudy.service.impl.SortingTaskElementServiceImpl;
import com.example.estudy.web.dto.lesson.SortingTaskElementDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.mappers.SortingTaskElementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practical/sort/element")
@RequiredArgsConstructor
@Validated
public class SortingTaskElementController {

    private final SortingTaskElementServiceImpl elementService;

    private final SortingTaskElementMapper elementMapper;

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) SortingTaskElementDto elementDto, Long taskId) {
        SortingTaskElement element = elementMapper.toEntity(elementDto);
        elementService.create(element, taskId);
        return "redirect:/practical?id=" + element.getTask().getPracticalContent().getId();
    }

    @PostMapping("/delete")
    public String delete(Long item_id) {
        Long contentId = elementService.getById(item_id).getTask().getPracticalContent().getId();
        elementService.delete(item_id);
        return "redirect:/practical?id=" + contentId;
    }

}
