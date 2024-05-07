package com.example.estudy.repository.course.content.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    List<Chapter> findAllByTheoreticalContent_Id(Long id);

}
