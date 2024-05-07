package com.example.estudy.service.dao.course.content.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Text;

import java.util.List;

public interface TextService {

    Text getById(Long id);

    List<Text> getAllByChapterId(Long id);

    Text create(Text text, Long chapterId);

    Text update(Text text, Long textId);

    void delete(Long id);

}
