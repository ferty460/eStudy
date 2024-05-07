package com.example.estudy.service.dao.course.content.practical;

import com.example.estudy.domain.lesson.content.practical.SortingTaskElement;

import java.util.List;

public interface SortingTaskElementService {

    SortingTaskElement getById(Long id);

    List<SortingTaskElement> getAllByTaskId(Long id);

    SortingTaskElement create(SortingTaskElement element, Long taskId);

    SortingTaskElement update(SortingTaskElement element, Long elementId);

    void delete(Long id);

}
