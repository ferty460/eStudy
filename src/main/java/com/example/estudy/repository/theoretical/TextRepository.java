package com.example.estudy.repository.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Long> {

    List<Text> findAllByChapter_Id(Long id);

}
