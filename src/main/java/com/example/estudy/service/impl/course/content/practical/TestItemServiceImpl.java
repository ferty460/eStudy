package com.example.estudy.service.impl.course.content.practical;

import com.example.estudy.domain.lesson.content.practical.Test;
import com.example.estudy.domain.lesson.content.practical.TestItem;
import com.example.estudy.repository.course.content.practical.TestItemRepository;
import com.example.estudy.repository.course.content.practical.TestRepository;
import com.example.estudy.service.dao.course.content.practical.TestItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestItemServiceImpl implements TestItemService {

    private final TestItemRepository testItemRepository;
    private final TestRepository testRepository;

    @Override
    public TestItem getById(Long id) {
        return testItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Test item not found"));
    }

    @Override
    public List<TestItem> getAllByTestId(Long id) {
        return testItemRepository.findAllByTestId(id);
    }

    @Override
    public TestItem create(TestItem item, Long testId) {
        item.setTest(testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found")));

        log.info("Saving new Test item: value = {}, testId = {}", item.getValue(), testId);
        return testItemRepository.save(item);
    }

    @Override
    public TestItem update(TestItem item, Long itemId) {
        TestItem editedItem = testItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Test item not found"));
        editedItem.setValue(item.getValue());

        log.info("Editing Test item with id {}", itemId);
        return testItemRepository.save(editedItem);
    }

    @Override
    public TestItem getByRightAndTestId(Long testId) {
        return testItemRepository.findByTestIdAndIsRightTrue(testId);
    }

    @Override
    public void delete(Long id) {
        TestItem item = testItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test item not found"));
        Test test = item.getTest();
        test.getItems().remove(item);
        testRepository.save(test);
        testItemRepository.deleteById(id);
        log.info("Deleting Test item with id {}", id);
    }
}
