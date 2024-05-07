package com.example.estudy.service.dao.course.content.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.Test;

import java.util.List;

public interface TestService {

    Test getById(Long id);

    List<Test> getAllByPracticalContentId(Long id);

    Test create(PracticalContent content);

    Test update(Test test, Long testId, Long itemId);

    Test update(Test test, Long testId);

    void delete(Long id);

}
