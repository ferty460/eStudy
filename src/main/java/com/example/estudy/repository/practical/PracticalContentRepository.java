package com.example.estudy.repository.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PracticalContentRepository extends JpaRepository<PracticalContent, Long> {

    List<PracticalContent> findAllByLesson_Id(Long id);

}
