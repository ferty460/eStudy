package com.example.estudy.repository.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Title;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, Long> {

    List<Title> findAllByChapter_Id(Long id);

}
