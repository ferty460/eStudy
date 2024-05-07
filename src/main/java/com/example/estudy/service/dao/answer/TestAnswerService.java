package com.example.estudy.service.dao.answer;

import com.example.estudy.domain.answer.TestAnswer;

public interface TestAnswerService {

    TestAnswer getById(Long id);

    TestAnswer create(TestAnswer answer, Long testId, Long userId);

    TestAnswer update(TestAnswer answer, Long answerId);

    void delete(Long id);

}
