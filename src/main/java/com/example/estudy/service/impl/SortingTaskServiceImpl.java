package com.example.estudy.service.impl;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.SortingTask;
import com.example.estudy.domain.lesson.content.practical.SortingTaskElement;
import com.example.estudy.repository.practical.SortingTaskElementRepository;
import com.example.estudy.repository.practical.SortingTaskRepository;
import com.example.estudy.service.SortingTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SortingTaskServiceImpl implements SortingTaskService {

    private final SortingTaskRepository taskRepository;
    private final SortingTaskElementRepository elementRepository;

    @Override
    public SortingTask getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sorting task not found"));
    }

    @Override
    public List<SortingTask> getAllByPracticalContentId(Long id) {
        return taskRepository.findAllByPracticalContent_Id(id);
    }

    @Override
    public SortingTask create(PracticalContent content) {
        SortingTask task = new SortingTask();
        content.setSortingTask(task);
        task.setTitle(content.getTitle());
        task.setDescription(content.getDescription());
        task.setPracticalContent(content);

        for (int i = 1; i <= 4; i++) {
            SortingTaskElement element = new SortingTaskElement();
            element.setContent("Номер " + i);
            element.setPosition(i);
            element.setTask(task);
            elementRepository.save(element);
        }

        log.info("Saving new Sorting Task: contentId = {}, title = {}", content.getId(), task.getTitle());
        return taskRepository.save(task);
    }

    @Override
    public SortingTask update(SortingTask task, Long taskId) {
        SortingTask editedTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Sorting task not found"));
        editedTask.setTitle(task.getTitle());
        editedTask.setDescription(task.getDescription());

        log.info("Editing Sorting task with id {}", taskId);
        return taskRepository.save(editedTask);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Sorting task with id {}", id);
        taskRepository.deleteById(id);
    }
}
