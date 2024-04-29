package com.example.estudy.service.impl;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.TextTask;
import com.example.estudy.repository.practical.TextTaskRepository;
import com.example.estudy.service.TextTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TextTaskServiceImpl implements TextTaskService {

    private final TextTaskRepository textTaskRepository;

    @Override
    public TextTask getById(Long id) {
        return textTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Text task not found"));
    }

    @Override
    public List<TextTask> getAllByPracticalContentId(Long id) {
        return textTaskRepository.findAllByPracticalContent_Id(id);
    }

    @Override
    public TextTask create(PracticalContent content) {
        TextTask textTask = new TextTask();
        content.setTextTask(textTask);
        textTask.setTitle(content.getTitle());
        textTask.setDescription(content.getDescription());
        textTask.setPracticalContent(content);

        log.info("Saving new Text task: contentId = {}, title = {}", content.getId(), textTask.getTitle());
        return textTaskRepository.save(textTask);
    }

    @Override
    public TextTask update(TextTask textTask, Long textTaskId) {
        TextTask editedTextTask = textTaskRepository.findById(textTaskId)
                .orElseThrow(() -> new RuntimeException("Text task not found"));
        editedTextTask.setTitle(textTask.getTitle());
        editedTextTask.setDescription(textTask.getDescription());
        editedTextTask.setCorrectAnswer(textTask.getCorrectAnswer());

        log.info("Editing Text task with id {}", editedTextTask.getId());
        return textTaskRepository.save(editedTextTask);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Text task with id {}", id);
        textTaskRepository.deleteById(id);
    }

}
