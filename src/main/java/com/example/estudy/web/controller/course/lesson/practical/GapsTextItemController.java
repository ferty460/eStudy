package com.example.estudy.web.controller.course.lesson.practical;

import com.example.estudy.domain.lesson.content.practical.GapsTextItem;
import com.example.estudy.service.impl.course.content.practical.GapsTextItemServiceImpl;
import com.example.estudy.web.dto.lesson.GapsTextItemDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.mappers.GapsTextItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practical/gaps/text")
@RequiredArgsConstructor
@Validated
public class GapsTextItemController {

    private final GapsTextItemServiceImpl itemService;

    private final GapsTextItemMapper itemMapper;

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) GapsTextItemDto itemDto, Long taskId) {
        GapsTextItem item = itemMapper.toEntity(itemDto);
        itemService.create(item, taskId);
        return "redirect:/practical?id=" + item.getTask().getPracticalContent().getId();
    }

    @PostMapping("/delete")
    public String delete(Long item_id) {
        GapsTextItem item = itemService.getById(item_id);
        itemService.delete(item_id);
        return "redirect:/practical?id=" + item.getTask().getPracticalContent().getId();
    }

}
