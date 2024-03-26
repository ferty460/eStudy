package com.example.estudy.service;

import com.example.estudy.domain.course.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseService {

    Course getById(Long id);

    List<Course> getAllByUserId(Long id);

    Course update(Course course, Long courseId, MultipartFile file) throws IOException;

    Course create(Course course, Long userId, MultipartFile file) throws IOException;

    void delete(Long id);

}
