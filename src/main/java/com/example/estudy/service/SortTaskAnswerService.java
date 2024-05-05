package com.example.estudy.service;

import com.example.estudy.domain.answer.SortTaskAnswer;

import java.util.List;

public interface SortTaskAnswerService {

    SortTaskAnswer getById(Long id);

    SortTaskAnswer create(SortTaskAnswer answer, Long taskId, Long userId, List<String> order);

    SortTaskAnswer update(SortTaskAnswer answer, Long answerId, List<String> order);

    void delete(Long id);

}
