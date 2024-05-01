package com.example.estudy.service.impl;

import com.example.estudy.domain.lesson.content.practical.GapsTextItem;
import com.example.estudy.repository.practical.GapsTaskRepository;
import com.example.estudy.repository.practical.GapsTextItemRepository;
import com.example.estudy.service.GapsTextItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GapsTextItemServiceImpl implements GapsTextItemService {

    private final GapsTextItemRepository itemRepository;
    private final GapsTaskRepository taskRepository;

    @Override
    public GapsTextItem getById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public List<GapsTextItem> getAllByTaskId(Long id) {
        return itemRepository.findAllByTaskId(id);
    }

    @Override
    public GapsTextItem create(GapsTextItem item, Long taskId) {
        item.setTask(taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found")));

        log.info("Saving new Gaps text item: taskId = {}, text = {}", taskId, item.getText());
        return itemRepository.save(item);
    }

    @Override
    public GapsTextItem update(GapsTextItem item, Long itemId) {
        GapsTextItem editedItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        editedItem.setText(item.getText());

        log.info("Editing Gaps text item with id {}", itemId);
        return itemRepository.save(editedItem);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Gaps text item with id {}", id);
        itemRepository.deleteById(id);
    }

}
