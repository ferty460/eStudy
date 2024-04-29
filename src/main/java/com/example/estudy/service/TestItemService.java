package com.example.estudy.service;

import com.example.estudy.domain.lesson.content.practical.TestItem;

import java.util.List;

public interface TestItemService {

    TestItem getById(Long id);

    List<TestItem> getAllByTestId(Long id);

    TestItem create(TestItem item, Long testId);

    TestItem update(TestItem item, Long itemId);

    TestItem getByRightAndTestId(Long testId);

    void delete(Long id);

}
