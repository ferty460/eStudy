package com.example.estudy.service.impl;

import com.example.estudy.domain.lesson.Lesson;
import com.example.estudy.repository.LessonRepository;
import com.example.estudy.repository.ModuleRepository;
import com.example.estudy.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;

    @Override
    public Lesson getById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    @Override
    public List<Lesson> getAllByModuleId(Long id) {
        return lessonRepository.findAllByModule_Id(id);
    }

    @Override
    public Lesson create(Lesson lesson, Long moduleId) {
        lesson.setModule(moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found")));

        log.info("Saving new Lesson: title = {}, date of created = {}, authorId = {}",
                lesson.getTitle(), lesson.getDateOfCreated(), lesson.getModule().getCourse().getAuthor().getId());
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson update(Lesson lesson, Long lessonId) {
        Lesson editedLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        editedLesson.setTitle(lesson.getTitle());
        editedLesson.setDescription(lesson.getDescription());

        log.info("Editing Lesson with id {}", editedLesson.getId());
        return lessonRepository.save(editedLesson);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Lesson with id {}", id);
        lessonRepository.deleteById(id);
    }

}
