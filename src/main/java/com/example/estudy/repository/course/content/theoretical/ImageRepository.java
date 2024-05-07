package com.example.estudy.repository.course.content.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByChapter_Id(Long id);

}
