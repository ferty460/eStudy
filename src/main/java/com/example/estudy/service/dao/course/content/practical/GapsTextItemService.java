package com.example.estudy.service.dao.course.content.practical;

import com.example.estudy.domain.lesson.content.practical.GapsTextItem;

import java.util.List;

public interface GapsTextItemService {

    GapsTextItem getById(Long id);

    List<GapsTextItem> getAllByTaskId(Long id);

    GapsTextItem create(GapsTextItem item, Long taskId);

    GapsTextItem update(GapsTextItem item, Long itemId);

    void delete(Long id);

}
