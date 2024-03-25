package com.example.estudy.service;

import com.example.estudy.domain.course.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseService {

    Course getById(Long id);

    List<Course> getAllByUserId(Long id);

    Course update(Course course, Long courseId);

    Course create(Course course, Long id, MultipartFile file) throws IOException;

    void delete(Long id);

}
