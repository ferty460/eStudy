package com.example.estudy.repository.course;

import com.example.estudy.domain.lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByModule_Id(Long id);

}
