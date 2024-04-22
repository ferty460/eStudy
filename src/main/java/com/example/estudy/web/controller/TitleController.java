package com.example.estudy.web.controller;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;
import com.example.estudy.domain.lesson.content.theoretical.Title;
import com.example.estudy.service.impl.ChapterServiceImpl;
import com.example.estudy.service.impl.TitleServiceImpl;
import com.example.estudy.web.dto.lesson.TitleDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.mappers.TitleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/titles")
@RequiredArgsConstructor
@Validated
public class TitleController {

    private final TitleServiceImpl titleService;
    private final ChapterServiceImpl chapterService;

    private final TitleMapper titleMapper;

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) TitleDto titleDto, Long chapterId) {
        Title title = titleMapper.toEntity(titleDto);
        Chapter chapter = chapterService.getById(chapterId);
        titleService.create(title, chapterId);
        return "redirect:/theoretical?id=" + chapter.getTheoreticalContent().getId();
    }

}
