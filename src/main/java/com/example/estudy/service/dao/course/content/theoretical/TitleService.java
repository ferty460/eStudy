package com.example.estudy.service.dao.course.content.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Title;

import java.util.List;

public interface TitleService {

    Title getById(Long id);

    List<Title> getAllByChapterId(Long id);

    Title create(Title title, Long chapterId);

    Title update(Title title, Long titleId);

    void delete(Long id);

}
