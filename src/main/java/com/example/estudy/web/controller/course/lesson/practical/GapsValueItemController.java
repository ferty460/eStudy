package com.example.estudy.web.controller.course.lesson.practical;

import com.example.estudy.domain.lesson.content.practical.GapsValueItem;
import com.example.estudy.service.impl.GapsValueItemServiceImpl;
import com.example.estudy.web.dto.lesson.GapsValueItemDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.mappers.GapsValueItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practical/gaps/value")
@RequiredArgsConstructor
@Validated
public class GapsValueItemController {

    private final GapsValueItemServiceImpl itemService;

    private final GapsValueItemMapper itemMapper;

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) GapsValueItemDto itemDto, Long taskId) {
        GapsValueItem item = itemMapper.toEntity(itemDto);
        itemService.create(item, taskId);
        return "redirect:/practical?id=" + item.getTask().getPracticalContent().getId();
    }

    @PostMapping("/delete")
    public String delete(Long item_id) {
        GapsValueItem item = itemService.getById(item_id);
        itemService.delete(item_id);
        return "redirect:/practical?id=" + item.getTask().getPracticalContent().getId();
    }

}
