package com.example.estudy.repository.course.content.practical;

import com.example.estudy.domain.lesson.content.practical.GapsValueItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GapsValueItemRepository extends JpaRepository<GapsValueItem, Long> {

    List<GapsValueItem> findAllByTaskId(Long id);

}
