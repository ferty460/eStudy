package com.example.estudy.service;

import com.example.estudy.domain.lesson.content.PracticalContent;

import java.util.List;

public interface PracticalContentService {

    PracticalContent getById(Long id);

    List<PracticalContent> getAllByLessonId(Long id);

    PracticalContent create(PracticalContent content, Long lessonId);

    PracticalContent update(PracticalContent content, Long contentId);

    void delete(Long id);

}
