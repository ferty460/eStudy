package com.example.estudy.web.controller;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;
import com.example.estudy.service.impl.ChapterServiceImpl;
import com.example.estudy.web.dto.lesson.ChapterDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.mappers.ChapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chapters")
@RequiredArgsConstructor
@Validated
public class ChapterController {

    private final ChapterServiceImpl chapterService;

    private final ChapterMapper chapterMapper;

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) ChapterDto chapterDto, Long contentId) {
        Chapter chapter = chapterMapper.toEntity(chapterDto);
        chapterService.create(chapter, contentId);
        return "redirect:/theoretical?id=" + contentId;
    }

}
