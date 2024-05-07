package com.example.estudy.service.dao.course.content;

import com.example.estudy.domain.lesson.content.TheoreticalContent;

import java.util.List;

public interface TheoreticalContentService {

    TheoreticalContent getById(Long id);

    List<TheoreticalContent> getAllByLessonId(Long id);

    TheoreticalContent create(TheoreticalContent content, Long lessonId);

    TheoreticalContent update(TheoreticalContent content, Long contentId);

    void delete(Long id);

}
