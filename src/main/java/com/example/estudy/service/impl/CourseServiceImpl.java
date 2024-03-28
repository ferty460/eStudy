package com.example.estudy.service.impl;

import com.example.estudy.domain.course.Availability;
import com.example.estudy.domain.course.Course;
import com.example.estudy.domain.course.CourseImage;
import com.example.estudy.domain.user.User;
import com.example.estudy.repository.CourseImageRepository;
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
    private final CourseImageRepository imageRepository;

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
    @Transactional(readOnly = true)
    public List<Course> getAllByAvailability(Availability availability) {
        return courseRepository.findAllByAvailability(availability);
    }

    public void addFollowerToCourse(User follower, Course course) {
        course.addFollower(follower);
        courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course create(Course course, Long userId, MultipartFile file) throws IOException {
        course.setAuthor(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id " + userId + " not found")));
        course.setRating(0.0F);
        if (file.getSize() != 0) {
            CourseImage image = toImageEntity(file);
            course.setImage(image);
        }
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course update(Course course, Long courseId, MultipartFile file) throws IOException {
        Course editedCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        editedCourse.setTitle(course.getTitle());
        editedCourse.setDescription(course.getDescription());
        if (file.getSize() != 0) {
            CourseImage image = toImageEntity(file);
            CourseImage oldImage = imageRepository.findById(editedCourse.getImage().getId())
                    .orElseThrow(() -> new RuntimeException("Image not found"));
            imageRepository.deleteById(oldImage.getId());
            editedCourse.setImage(image);
        }

        return courseRepository.save(editedCourse);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    /* --------- IMAGE --------- */

    private CourseImage toImageEntity(MultipartFile file) throws IOException {
        CourseImage image = new CourseImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());

        return image;
    }

}
