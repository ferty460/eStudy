package com.example.estudy.repository.practical;

import com.example.estudy.domain.lesson.content.practical.TextTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextTaskRepository extends JpaRepository<TextTask, Long> {

    List<TextTask> findAllByPracticalContent_Id(Long id);

}
