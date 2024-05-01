package com.example.estudy.service.impl;

import com.example.estudy.domain.lesson.content.practical.GapsValueItem;
import com.example.estudy.repository.practical.GapsTaskRepository;
import com.example.estudy.repository.practical.GapsValueItemRepository;
import com.example.estudy.service.GapsValueItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GapsValueItemServiceImpl implements GapsValueItemService {

    private final GapsValueItemRepository itemRepository;
    private final GapsTaskRepository taskRepository;

    @Override
    public GapsValueItem getById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public List<GapsValueItem> getAllByTaskId(Long id) {
        return itemRepository.findAllByTaskId(id);
    }

    @Override
    public GapsValueItem create(GapsValueItem item, Long taskId) {
        item.setTask(taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found")));

        log.info("Saving new Gaps value item: taskId = {}, value = {}", taskId, item.getValue());
        return itemRepository.save(item);
    }

    @Override
    public GapsValueItem update(GapsValueItem item, Long itemId) {
        GapsValueItem editedItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        editedItem.setValue(item.getValue());

        log.info("Editing Gaps value item with id {}", itemId);
        return itemRepository.save(editedItem);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Gaps value item with id {}", id);
        itemRepository.deleteById(id);
    }

}
