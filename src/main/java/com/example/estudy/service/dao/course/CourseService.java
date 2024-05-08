package com.example.estudy.service.dao.course;

import com.example.estudy.domain.course.Availability;
import com.example.estudy.domain.course.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseService {

    Course getById(Long id);

    List<Course> getAllByUserId(Long id);

    List<Course> getAllByAvailability(Availability availability);

    List<Course> getAllByTagName(String tagName, Availability availability);

    List<Course> getTop5ByRating(Availability availability);

    Course update(Course course, Long courseId, MultipartFile file) throws IOException;

    Course create(Course course, Long userId, MultipartFile file) throws IOException;

    void delete(Long id);

}
