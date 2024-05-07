package com.example.estudy.repository.course.content.practical;

import com.example.estudy.domain.lesson.content.practical.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findAllByPracticalContent_Id(Long id);

}
