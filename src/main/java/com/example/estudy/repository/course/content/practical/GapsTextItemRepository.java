package com.example.estudy.repository.course.content.practical;

import com.example.estudy.domain.lesson.content.practical.GapsTextItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GapsTextItemRepository extends JpaRepository<GapsTextItem, Long> {

    List<GapsTextItem> findAllByTaskId(Long id);

}
