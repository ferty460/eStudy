package com.example.estudy.repository.practical;

import com.example.estudy.domain.lesson.content.practical.SortingTaskElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SortingTaskElementRepository extends JpaRepository<SortingTaskElement, Long> {

    List<SortingTaskElement> findAllByTaskId(Long id);

}
