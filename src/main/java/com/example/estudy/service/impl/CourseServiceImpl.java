package com.example.estudy.service.impl;

import com.example.estudy.domain.course.Course;
import com.example.estudy.domain.course.CourseImage;
import com.example.estudy.repository.CourseRepository;
import com.example.estudy.repository.UserRepository;
import com.example.estudy.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course with id " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAllByUserId(Long id) {
        return courseRepository.findAllByAuthorId(id);
    }

    @Override
    @Transactional
    public Course update(Course course, Long courseId) {
        Course editedCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        editedCourse.setAuthor(course.getAuthor());
        editedCourse.setTag(course.getTag());
        editedCourse.setTitle(course.getTitle());
        editedCourse.setDescription(course.getDescription());
        editedCourse.setRating(course.getRating());

        return courseRepository.save(editedCourse);
    }

    @Override
    @Transactional
    public Course create(Course course, Long userId, MultipartFile file) throws IOException {
        course.setAuthor(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id " + userId + " not found")));
        course.setRating(0.0F);
        addImage(file, course);
        Course courseFromDB = courseRepository.save(course);
        courseFromDB.setPreviewImageId(courseFromDB.getImages().get(0).getId());

        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    /* --------- IMAGE --------- */

    private void addImage(MultipartFile file, Course course) throws IOException {
        CourseImage image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            course.addImageToCourse(image);
        }
    }

    private CourseImage toImageEntity(MultipartFile file) throws IOException {
        CourseImage image = new CourseImage();
        image.setName(file.getName());
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());

        return image;
    }

}
