package com.example.estudy.service;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.TextTask;

import java.util.List;

public interface TextTaskService {

    TextTask getById(Long id);

    List<TextTask> getAllByPracticalContentId(Long id);

    TextTask create(PracticalContent content);

    TextTask update(TextTask textTask, Long textTaskId);

    void delete(Long id);

}
