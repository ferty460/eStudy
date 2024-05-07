package com.example.estudy.service.dao.answer;

import com.example.estudy.domain.answer.GapsTaskAnswer;

import java.util.Map;

public interface GapsTaskAnswerService {

    GapsTaskAnswer getById(Long id);

    GapsTaskAnswer getByGapsTaskIdAndUserId(Long taskId, Long userId);

    GapsTaskAnswer create(GapsTaskAnswer answer, Long taskId, Long userId, Map<String, String> gaps);

    GapsTaskAnswer update(GapsTaskAnswer answer, Long answerId, Map<String, String> gaps);

    void delete(Long id);

}
