package com.example.estudy.web.controller.news;

import com.example.estudy.domain.news.NewsItem;
import com.example.estudy.service.impl.news.NewsItemServiceImpl;
import com.example.estudy.web.dto.news.NewsItemDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.NewsItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notes")
@Validated
public class NewsItemController {

    private final NewsItemServiceImpl newsItemService;
    private final NewsItemMapper newsItemMapper;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODER')")
    public String create(@Validated(OnCreate.class) NewsItemDto itemDto, Long newsId) {
        NewsItem item = newsItemMapper.toEntity(itemDto);
        newsItemService.create(item, newsId);
        return "redirect:/news/read?id=" + newsId;
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODER')")
    public String edit(@Validated(OnUpdate.class) NewsItemDto itemDto, Long id) {
        NewsItem item = newsItemMapper.toEntity(itemDto);
        NewsItem editedItem = newsItemService.update(item, id);
        return "redirect:/news/read?id=" + editedItem.getNews().getId();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODER')")
    public String edit(Long id) {
        Long newsId = newsItemService.getById(id).getNews().getId();
        newsItemService.delete(id);
        return "redirect:/news/read?id=" + newsId;
    }

}
