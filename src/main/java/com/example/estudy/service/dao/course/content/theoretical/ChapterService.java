package com.example.estudy.service.dao.course.content.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;

import java.util.List;

public interface ChapterService {

    Chapter getById(Long id);

    List<Chapter> getAllByTheoreticalContentId(Long contentId);

    Chapter create(Chapter chapter, Long contentId);

    Chapter update(Chapter chapter, Long chapterId);

    void delete(Long id);

}
