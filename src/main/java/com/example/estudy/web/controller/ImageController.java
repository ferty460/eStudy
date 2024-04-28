package com.example.estudy.web.controller;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;
import com.example.estudy.domain.lesson.content.theoretical.Image;
import com.example.estudy.service.impl.ChapterServiceImpl;
import com.example.estudy.service.impl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageServiceImpl imageService;
    private final ChapterServiceImpl chapterService;

    @GetMapping("/theoretical/img/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image image = imageService.getById(id);
        assert image != null;
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    @PostMapping("/images/create")
    public String createImage(@RequestParam("image") MultipartFile file, Long chapterId) {
        Chapter chapter = chapterService.getById(chapterId);
        imageService.create(file, chapterId);
        return "redirect:/theoretical?id=" + chapter.getTheoreticalContent().getId();
    }

    @PostMapping("/images/update")
    public String update(@RequestParam("image") MultipartFile file, Long id, Long chapterId) {
        Chapter chapter = chapterService.getById(chapterId);
        imageService.update(file, id);
        return "redirect:/theoretical?id=" + chapter.getTheoreticalContent().getId();
    }

    @PostMapping("/images/delete")
    public String delete(Long id) {
        Long lessonId = imageService.getById(id).getChapter().getTheoreticalContent().getId();
        imageService.delete(id);
        return "redirect:/theoretical?id=" + lessonId;
    }

}
