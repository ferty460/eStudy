package com.example.estudy.service.impl.course.content.practical;

import com.example.estudy.domain.lesson.content.practical.SortingTaskElement;
import com.example.estudy.repository.course.content.practical.SortingTaskElementRepository;
import com.example.estudy.repository.course.content.practical.SortingTaskRepository;
import com.example.estudy.service.dao.course.content.practical.SortingTaskElementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SortingTaskElementServiceImpl implements SortingTaskElementService {

    private final SortingTaskElementRepository elementRepository;
    private final SortingTaskRepository taskRepository;

    @Override
    public SortingTaskElement getById(Long id) {
        return elementRepository.findById(id).orElseThrow(() -> new RuntimeException("Element not found"));
    }

    @Override
    public List<SortingTaskElement> getAllByTaskId(Long id) {
        return elementRepository.findAllByTaskId(id);
    }

    @Override
    public SortingTaskElement create(SortingTaskElement element, Long taskId) {
        element.setTask(taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Sorting task not found")));
        element.setPosition(elementRepository.findAll().size());

        log.info("Saving new Sorting task element: taskId = {}", taskId);
        return elementRepository.save(element);
    }

    @Override
    public SortingTaskElement update(SortingTaskElement element, Long elementId) {
        SortingTaskElement editedElement = elementRepository.findById(elementId)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        editedElement.setContent(element.getContent());
        editedElement.setPosition(element.getPosition());

        log.info("Editing Sorting task element with id {}", elementId);
        return elementRepository.save(editedElement);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Sorting task element with id {}", id);
        elementRepository.deleteById(id);
    }

    public void updatePosition(Long elementId, int position) {
        SortingTaskElement element = elementRepository.findById(elementId)
                .orElseThrow(() -> new RuntimeException("Element not found"));
        element.setPosition(position);
        elementRepository.save(element);
    }

}
