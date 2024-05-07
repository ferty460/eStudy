package com.example.estudy.service.dao.FAQ;

import com.example.estudy.domain.FAQ.QuestionItem;

import java.util.List;

public interface QuestionItemService {

    QuestionItem getById(Long id);

    List<QuestionItem> getAll();

    List<QuestionItem> getAllByQuestionId(Long id);

    QuestionItem update(QuestionItem item, Long itemId);

    QuestionItem create(QuestionItem item, Long newsId);

    void delete(Long id);

    void deleteAllByQuestionId(Long id);

}
