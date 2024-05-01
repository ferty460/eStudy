package com.example.estudy.repository.practical;

import com.example.estudy.domain.lesson.content.practical.GapsTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GapsTaskRepository extends JpaRepository<GapsTask, Long> {

    List<GapsTask> findAllByPracticalContent_Id(Long id);

}
