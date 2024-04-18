package com.example.estudy.service;

import com.example.estudy.domain.lesson.Lesson;

import java.util.List;

public interface LessonService {

    Lesson getById(Long id);

    List<Lesson> getAllByModuleId(Long id);

    Lesson create(Lesson lesson, Long moduleId);

    Lesson update(Lesson lesson, Long lessonId);

    void delete(Long id);

}
