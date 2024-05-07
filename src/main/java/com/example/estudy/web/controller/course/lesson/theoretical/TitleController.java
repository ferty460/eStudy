package com.example.estudy.web.controller.course.lesson.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;
import com.example.estudy.domain.lesson.content.theoretical.Title;
import com.example.estudy.service.impl.course.content.theoretical.ChapterServiceImpl;
import com.example.estudy.service.impl.course.content.theoretical.TitleServiceImpl;
import com.example.estudy.web.dto.lesson.TitleDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.TitleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@chapterServiceImpl.getById(#chapterId).theoreticalContent.lesson.module.course.author.id == authentication.principal.id")
    public String create(@Validated(OnCreate.class) TitleDto titleDto, Long chapterId) {
        Title title = titleMapper.toEntity(titleDto);
        Chapter chapter = chapterService.getById(chapterId);
        titleService.create(title, chapterId);
        return "redirect:/theoretical?id=" + chapter.getTheoreticalContent().getId();
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@titleServiceImpl.getById(#titleDto.id).chapter.theoreticalContent.lesson.module.course.author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) TitleDto titleDto) {
        Title title = titleMapper.toEntity(titleDto);
        Title editedTitle = titleService.update(title, title.getId());
        return "redirect:/theoretical?id=" + editedTitle.getChapter().getTheoreticalContent().getId();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@titleServiceImpl.getById(#id).chapter.theoreticalContent.lesson.module.course.author.id == authentication.principal.id")
    public String delete(Long id) {
        Long lessonId = titleService.getById(id).getChapter().getTheoreticalContent().getId();
        titleService.delete(id);
        return "redirect:/theoretical?id=" + lessonId;
    }

}
