package com.example.estudy.web.controller;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;
import com.example.estudy.service.impl.ChapterServiceImpl;
import com.example.estudy.web.dto.lesson.ChapterDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
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

    @PostMapping("/update")
    public String update(@Validated(OnUpdate.class) ChapterDto chapterDto) {
        Chapter chapter = chapterMapper.toEntity(chapterDto);
        Chapter editedChapter = chapterService.update(chapter, chapter.getId());
        return "redirect:/theoretical?id=" + editedChapter.getTheoreticalContent().getId();
    }

    @PostMapping("/delete")
    public String delete(Long id) {
        Long lessonId = chapterService.getById(id).getTheoreticalContent().getId();
        chapterService.delete(id);
        return "redirect:/theoretical?id=" + lessonId;
    }

}
