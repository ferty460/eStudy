package com.example.estudy.web.controller.FAQ;

import com.example.estudy.domain.FAQ.QuestionItem;
import com.example.estudy.service.impl.FAQ.QuestionItemServiceImpl;
import com.example.estudy.web.dto.FAQ.QuestionItemDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.QuestionItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
@Validated
public class QuestionItemController {

    private final QuestionItemServiceImpl questionItemService;
    private final QuestionItemMapper itemMapper;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODER')")
    public String create(@Validated(OnCreate.class) QuestionItemDto itemDto, Long newsId) {
        QuestionItem item = itemMapper.toEntity(itemDto);
        questionItemService.create(item, newsId);
        return "redirect:/faq/read?id=" + newsId;
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODER')")
    public String edit(@Validated(OnUpdate.class) QuestionItemDto itemDto, Long id) {
        QuestionItem item = itemMapper.toEntity(itemDto);
        QuestionItem editedItem = questionItemService.update(item, id);
        return "redirect:/faq/read?id=" + editedItem.getQuestion().getId();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODER')")
    public String edit(Long id) {
        Long newsId = questionItemService.getById(id).getQuestion().getId();
        questionItemService.delete(id);
        return "redirect:/faq/read?id=" + newsId;
    }

}
