package com.example.estudy.web.controller;

import com.example.estudy.domain.lesson.content.practical.TestItem;
import com.example.estudy.service.impl.TestItemServiceImpl;
import com.example.estudy.web.dto.lesson.TestItemDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.mappers.TestItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practical/test/item")
@RequiredArgsConstructor
@Validated
public class TestItemController {

    private final TestItemServiceImpl itemService;

    private final TestItemMapper itemMapper;

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) TestItemDto testItemDto, Long testId) {
        TestItem testItem = itemMapper.toEntity(testItemDto);
        itemService.create(testItem, testId);
        return "redirect:/practical?id=" + testItem.getTest().getPracticalContent().getId();
    }

    @PostMapping("/delete")
    public String delete(Long item_id) {
        Long contentId = itemService.getById(item_id).getTest().getPracticalContent().getId();
        itemService.delete(item_id);
        return "redirect:/practical?id=" + contentId;
    }

}
