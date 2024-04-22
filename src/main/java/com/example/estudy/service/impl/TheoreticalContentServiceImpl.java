package com.example.estudy.service.impl;

import com.example.estudy.domain.lesson.content.TheoreticalContent;
import com.example.estudy.repository.LessonRepository;
import com.example.estudy.repository.theoretical.TheoreticalContentRepository;
import com.example.estudy.service.TheoreticalContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TheoreticalContentServiceImpl implements TheoreticalContentService {

    private final TheoreticalContentRepository contentRepository;
    private final LessonRepository lessonRepository;

    @Override
    public TheoreticalContent getById(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theoretical content not found"));
    }

    @Override
    public List<TheoreticalContent> getAllByLessonId(Long id) {
        return contentRepository.findAllByLesson_Id(id);
    }

    @Override
    public TheoreticalContent create(TheoreticalContent content, Long lessonId) {
        content.setLesson(lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found")));

        log.info("Saving new Theoretical Content: title = {}, lessonId = {}",
                content.getTitle(), content.getLesson().getId());
        return contentRepository.save(content);
    }

    @Override
    public TheoreticalContent update(TheoreticalContent content, Long contentId) {
        TheoreticalContent editedContent = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Theoretical content not found"));
        editedContent.setTitle(content.getTitle());
        editedContent.setDescription(content.getDescription());

        log.info("Editing Theoretical content with id {}", contentId);
        return contentRepository.save(editedContent);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Theoretical content with id {}", id);
        contentRepository.deleteById(id);
    }

}
