package com.example.estudy.repository.course;

import com.example.estudy.domain.course.CourseAccessLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseAccessLinkRepository extends JpaRepository<CourseAccessLink, Long> {

    boolean existsByCourseIdAndAccessCode(Long id, String code);

    boolean existsByAccessCode(String code);

    Optional<CourseAccessLink> findByAccessCode(String accessCode);

}
