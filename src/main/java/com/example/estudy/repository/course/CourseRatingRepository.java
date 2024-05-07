package com.example.estudy.repository.course;

import com.example.estudy.domain.course.CourseRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRatingRepository extends JpaRepository<CourseRating, Long> {

    List<CourseRating> findAllByCourseId(Long id);

    List<CourseRating> findAllByCourseIdAndUserId(Long courseId, Long userId);

}
