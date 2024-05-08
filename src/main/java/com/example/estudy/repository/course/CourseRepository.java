package com.example.estudy.repository.course;

import com.example.estudy.domain.course.Availability;
import com.example.estudy.domain.course.Course;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByAuthorId(Long id);

    List<Course> findAllByAvailability(Availability availability);

    List<Course> findAllByAvailability(Availability availability, Sort sort);

    List<Course> findAllByTagNameAndAvailability(String tagName, Availability availability);

    List<Course> findAllByTitleContainingIgnoreCaseOrAuthorUsernameContainingIgnoreCaseAndAvailability(String tag, String username, Availability availability);

    List<Course> findFirst5ByAvailabilityOrderByRatingDesc(Availability availability);

}
