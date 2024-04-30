package com.example.estudy.repository.practical;

import com.example.estudy.domain.lesson.content.practical.SortingTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SortingTaskRepository extends JpaRepository<SortingTask, Long> {

    List<SortingTask> findAllByPracticalContent_Id(Long id);

}
