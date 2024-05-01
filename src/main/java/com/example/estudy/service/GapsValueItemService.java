package com.example.estudy.service;

import com.example.estudy.domain.lesson.content.practical.GapsValueItem;

import java.util.List;

public interface GapsValueItemService {

    GapsValueItem getById(Long id);

    List<GapsValueItem> getAllByTaskId(Long id);

    GapsValueItem create(GapsValueItem item, Long taskId);

    GapsValueItem update(GapsValueItem item, Long itemId);

    void delete(Long id);

}
