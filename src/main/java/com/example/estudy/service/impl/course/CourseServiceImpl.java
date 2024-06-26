package com.example.estudy.service.impl.course;

import com.example.estudy.domain.course.*;
import com.example.estudy.domain.user.User;
import com.example.estudy.repository.course.CourseAccessLinkRepository;
import com.example.estudy.repository.course.CourseImageRepository;
import com.example.estudy.repository.course.CourseRatingRepository;
import com.example.estudy.repository.course.CourseRepository;
import com.example.estudy.repository.user.UserRepository;
import com.example.estudy.service.dao.course.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseImageRepository imageRepository;
    private final CourseRatingRepository ratingRepository;
    private final CourseAccessLinkRepository linkRepository;

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
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAllByAvailability(Availability availability) {
        return courseRepository.findAllByAvailability(availability);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAllByTagName(String tagName, Availability availability) {
        return courseRepository.findAllByTagNameAndAvailability(tagName, availability);
    }

    public List<Course> getAllByTitleAsc() {
        Sort sort = Sort.by("title").ascending();
        return courseRepository.findAllByAvailability(Availability.PUBLIC, sort);
    }

    public List<Course> getAllByTitleDesc() {
        Sort sort = Sort.by("title").descending();
        return courseRepository.findAllByAvailability(Availability.PUBLIC, sort);
    }

    public List<Course> getAllByRatingAsc() {
        Sort sort = Sort.by("rating").ascending();
        return courseRepository.findAllByAvailability(Availability.PUBLIC, sort);
    }

    public List<Course> getAllByRatingDesc() {
        Sort sort = Sort.by("rating").descending();
        return courseRepository.findAllByAvailability(Availability.PUBLIC, sort);
    }

    @Override
    public List<Course> getTop5ByRating(Availability availability) {
        return courseRepository.findFirst5ByAvailabilityOrderByRatingDesc(availability);
    }

    // Добавление поступивших пользователей на курс
    public void addFollowerToCourse(User follower, Course course) {
        course.addFollower(follower);
        courseRepository.save(course);
    }

    // Связка пользователей, добавивших курс в избранное, с соответствующим курсом
    public void addUserToCourse(User user, Course course) {
        course.addUser(user);
        courseRepository.save(course);
    }

    public long count() {
        return courseRepository.count();
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

        log.info("Saving new Course: title = {}, tag = {}, authorId = {}",
                course.getTitle(), course.getTag().getName(), course.getAuthor().getId());
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
            imageRepository.deleteById(editedCourse.getImage().getId());
            editedCourse.setImage(image);
        }

        log.info("Editing course with id {}", editedCourse.getId());
        return courseRepository.save(editedCourse);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting course with id {}", id);
        courseRepository.deleteById(id);
    }

    public boolean isRated(Long courseId, Long userId) {
        return !ratingRepository.findAllByCourseIdAndUserId(courseId, userId).isEmpty();
    }

    public void rate(Float value, Long courseId, Long userId) {
        Course ratedCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        User ratedUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CourseRating rating = new CourseRating();
        rating.setCourse(ratedCourse);
        rating.setUser(ratedUser);
        rating.setRating(value);

        ratingRepository.save(rating);

        Float newRating = ratingRepository.findAllByCourseId(courseId).stream()
                .map(CourseRating::getRating)
                .reduce(0f, Float::sum) / ratingRepository.findAllByCourseId(courseId).size();

        ratedCourse.setRating(newRating);
        courseRepository.save(ratedCourse);
    }

    public String generateAccessLink(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));

        String accessCode = UUID.randomUUID().toString();
        course.setAccessCode(accessCode);
        courseRepository.save(course);

        saveAccessLinkInfo(courseId, accessCode);

        String baseUrl = "http://localhost:8080/courses";
        return baseUrl + "?id=" + courseId + "&access_code=" + accessCode;
    }

    private void saveAccessLinkInfo(Long courseId, String accessCode) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));

        CourseAccessLink accessLink = new CourseAccessLink(course, accessCode);
        linkRepository.save(accessLink);
    }

    public boolean isPrivate(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course with id " + id + " not found"));

        return course.getAvailability() == Availability.PRIVATE;
    }

    public boolean isValidAccessCode(Long courseId, String accessCode) {
        if (!linkRepository.existsByAccessCode(accessCode)) return false;

        CourseAccessLink link = linkRepository.findByAccessCode(accessCode)
                .orElseThrow(() -> new RuntimeException("Course access link not found"));
        boolean result = link.isActive() && linkRepository.existsByCourseIdAndAccessCode(courseId, accessCode);
        if (result) {
            linkRepository.deleteById(link.getId());
        }

        return result;
    }

    public List<Course> getCourses(String tag, String query, String sort, Availability availability) {
        List<Course> courses;
        if (tag != null && !tag.isEmpty()) {
            courses = courseRepository.findAllByTagNameAndAvailability(tag, availability);
        } else if (query != null && !query.isEmpty()) {
            courses = courseRepository.findAllByTitleContainingIgnoreCaseOrAuthorUsernameContainingIgnoreCaseAndAvailability(query, query, availability);
        } else {
            courses = courseRepository.findAllByAvailability(availability);
        }

        if (sort != null) {
            courses = sortCourses(sort);
        }

        return courses;
    }

    private List<Course> sortCourses(String sort) {
        return switch (sort) {
            case "a-z" -> getAllByTitleAsc();
            case "z-a" -> getAllByTitleDesc();
            case "top" -> getAllByRatingDesc();
            case "bottom" -> getAllByRatingAsc();
            default -> courseRepository.findAllByAvailability(Availability.PUBLIC);
        };
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
