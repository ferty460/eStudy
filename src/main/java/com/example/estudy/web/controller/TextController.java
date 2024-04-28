package com.example.estudy.web.controller;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;
import com.example.estudy.domain.lesson.content.theoretical.Text;
import com.example.estudy.service.impl.ChapterServiceImpl;
import com.example.estudy.service.impl.TextServiceImpl;
import com.example.estudy.web.dto.lesson.TextDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.TextMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/texts")
@RequiredArgsConstructor
@Validated
public class TextController {

    private final TextServiceImpl textService;
    private final ChapterServiceImpl chapterService;

    private final TextMapper textMapper;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@chapterServiceImpl.getById(#chapterId).theoreticalContent.lesson.module.course.author.id == authentication.principal.id")
    public String create(@Validated(OnCreate.class) TextDto textDto, Long chapterId) {
        Text text = textMapper.toEntity(textDto);
        Chapter chapter = chapterService.getById(chapterId);
        textService.create(text, chapterId);
        return "redirect:/theoretical?id=" + chapter.getTheoreticalContent().getId();
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@textServiceImpl.getById(#textDto.id).chapter.theoreticalContent.lesson.module.course.author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) TextDto textDto) {
        Text text = textMapper.toEntity(textDto);
        Text editedText = textService.update(text, text.getId());
        return "redirect:/theoretical?id=" + editedText.getChapter().getTheoreticalContent().getId();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@titleServiceImpl.getById(#id).chapter.theoreticalContent.lesson.module.course.author.id == authentication.principal.id")
    public String delete(Long id) {
        Long lessonId = textService.getById(id).getChapter().getTheoreticalContent().getId();
        textService.delete(id);
        return "redirect:/theoretical?id=" + lessonId;
    }

}
