package com.example.estudy.service.impl.course.content.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.GapsTask;
import com.example.estudy.domain.lesson.content.practical.GapsTextItem;
import com.example.estudy.domain.lesson.content.practical.GapsValueItem;
import com.example.estudy.repository.course.content.practical.GapsTaskRepository;
import com.example.estudy.repository.course.content.practical.GapsTextItemRepository;
import com.example.estudy.repository.course.content.practical.GapsValueItemRepository;
import com.example.estudy.service.dao.course.content.practical.GapsTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GapsTaskServiceImpl implements GapsTaskService {

    private final GapsTaskRepository taskRepository;
    private final GapsTextItemRepository textItemRepository;
    private final GapsValueItemRepository valueItemRepository;

    @Override
    public GapsTask getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Gaps task not found"));
    }

    @Override
    public List<GapsTask> getAllByPracticalContentId(Long id) {
        return taskRepository.findAllByPracticalContent_Id(id);
    }

    @Override
    public GapsTask create(PracticalContent content) {
        GapsTask task = new GapsTask();
        content.setGapsTask(task);
        task.setTitle(content.getTitle());
        task.setDescription(content.getDescription());
        task.setPracticalContent(content);

        GapsTextItem textItem = new GapsTextItem();
        GapsValueItem valueItem = new GapsValueItem();
        textItem.setText("2 + 2 = ");
        textItem.setTask(task);
        valueItem.setValue("4");
        valueItem.setTask(task);
        textItemRepository.save(textItem);
        valueItemRepository.save(valueItem);

        log.info("Saving new Gaps task: contentId = {}, title = {}", content.getId(), task.getTitle());
        return taskRepository.save(task);
    }

    @Override
    public GapsTask update(GapsTask task, Long taskId) {
        GapsTask editedTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Gaps task not found"));
        editedTask.setTitle(task.getTitle());
        editedTask.setDescription(task.getDescription());

        log.info("Editing Gaps task with id {}", taskId);
        return taskRepository.save(editedTask);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Gaps task with id {}", id);
        taskRepository.deleteById(id);
    }

}
