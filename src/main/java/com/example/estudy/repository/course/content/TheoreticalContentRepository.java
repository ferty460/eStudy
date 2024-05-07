package com.example.estudy.repository.course.content;

import com.example.estudy.domain.lesson.content.TheoreticalContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheoreticalContentRepository extends JpaRepository<TheoreticalContent, Long> {

    List<TheoreticalContent> findAllByLesson_Id(Long id);

}
