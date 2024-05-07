package com.example.estudy.service.dao.course.content.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.GapsTask;

import java.util.List;

public interface GapsTaskService {

    GapsTask getById(Long id);

    List<GapsTask> getAllByPracticalContentId(Long id);

    GapsTask create(PracticalContent content);

    GapsTask update(GapsTask task, Long taskId);

    void delete(Long id);

}
