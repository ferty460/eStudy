package com.example.estudy.service;

import com.example.estudy.domain.answer.TextTaskAnswer;

public interface TextTaskAnswerService {

    TextTaskAnswer getById(Long id);

    TextTaskAnswer create(TextTaskAnswer answer, Long taskId, Long userId);

    TextTaskAnswer update(TextTaskAnswer answer, Long taskId);

    void delete(Long id);

}
