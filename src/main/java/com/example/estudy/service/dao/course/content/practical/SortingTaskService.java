package com.example.estudy.service.dao.course.content.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.SortingTask;

import java.util.List;

public interface SortingTaskService {

    SortingTask getById(Long id);

    List<SortingTask> getAllByPracticalContentId(Long id);

    SortingTask create(PracticalContent practicalContent);

    SortingTask update(SortingTask task, Long taskId);

    void delete(Long id);

}
