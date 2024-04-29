package com.example.estudy.service.impl;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.Test;
import com.example.estudy.domain.lesson.content.practical.TestItem;
import com.example.estudy.repository.practical.TestItemRepository;
import com.example.estudy.repository.practical.TestRepository;
import com.example.estudy.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestItemRepository itemRepository;

    @Override
    public Test getById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new RuntimeException("Test not found"));
    }

    @Override
    public List<Test> getAllByPracticalContentId(Long id) {
        return testRepository.findAllByPracticalContent_Id(id);
    }

    @Override
    public Test create(PracticalContent content) {
        Test test = new Test();
        content.setTest(test);
        test.setTitle(content.getTitle());
        test.setDescription(content.getDescription());
        test.setPracticalContent(content);

        for (int i = 1; i <= 4; i++) {
            TestItem testItem = new TestItem();
            testItem.setValue("Номер " + i);
            testItem.setTest(test);
            itemRepository.save(testItem);
        }

        log.info("Saving new Test: contentId = {}, title = {}", content.getId(), test.getTitle());
        return testRepository.save(test);
    }

    @Override
    public Test update(Test test, Long testId, Long itemId) {
        Test editedTest = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
        editedTest.setTitle(test.getTitle());
        editedTest.setDescription(test.getDescription());

        TestItem item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        for (TestItem i : itemRepository.findAll()) {
            i.setRight(false);
        }
        item.setRight(true);
        itemRepository.save(item);

        log.info("Editing Text task with id {}", editedTest.getId());
        return testRepository.save(editedTest);
    }

    @Override
    public Test update(Test test, Long testId) {
        Test editedTest = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
        editedTest.setTitle(test.getTitle());
        editedTest.setDescription(test.getDescription());

        log.info("Editing Text task with id {}", editedTest.getId());
        return testRepository.save(editedTest);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Test with id {}", id);
        testRepository.deleteById(id);
    }

}
