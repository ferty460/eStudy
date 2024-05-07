package com.example.estudy.service.impl.course.content;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.repository.course.LessonRepository;
import com.example.estudy.repository.course.content.PracticalContentRepository;
import com.example.estudy.service.dao.course.content.PracticalContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PracticalContentServiceImpl implements PracticalContentService {

    private final PracticalContentRepository contentRepository;
    private final LessonRepository lessonRepository;

    @Override
    public PracticalContent getById(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Practical content not found"));
    }

    @Override
    public List<PracticalContent> getAllByLessonId(Long id) {
        return contentRepository.findAllByLesson_Id(id);
    }

    @Override
    public PracticalContent create(PracticalContent content, Long lessonId) {
        content.setLesson(lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found")));

        log.info("Saving new Practical content: title = {}, lessonId = {}",
                content.getTitle(), content.getLesson().getId());
        return contentRepository.save(content);
    }

    @Override
    public PracticalContent update(PracticalContent content, Long contentId) {
        PracticalContent editedContent = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Practical content not found"));
        editedContent.setTitle(content.getTitle());
        editedContent.setDescription(content.getDescription());

        log.info("Editing Practical content with id {}", contentId);
        return contentRepository.save(editedContent);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Practical content with id {}", id);
        contentRepository.deleteById(id);
    }

}
