package com.example.estudy.service.impl.user;

import com.example.estudy.domain.FAQ.Question;
import com.example.estudy.domain.course.Course;
import com.example.estudy.domain.news.News;
import com.example.estudy.domain.user.Role;
import com.example.estudy.domain.user.User;
import com.example.estudy.repository.FAQ.QuestionRepository;
import com.example.estudy.repository.course.CourseRepository;
import com.example.estudy.repository.news.NewsRepository;
import com.example.estudy.repository.user.UserRepository;
import com.example.estudy.service.dao.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final QuestionRepository questionRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("No User Found");

        return user;
    }

    @Override
    public User update(User user, Long id) {
        if (userRepository.findByUsername(user.getUsername()) != null
                || userRepository.findByEmail(user.getEmail()) != null) return null;

        User editedUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        editedUser.setName(user.getName());
        editedUser.setSurname(user.getSurname());
        editedUser.setPatronymic(user.getPatronymic());
        editedUser.setUsername(user.getUsername());
        editedUser.setEmail(user.getEmail());
        if (user.getGender() != null) editedUser.setGender(user.getGender());
        if (user.getAge() != null) editedUser.setAge(user.getAge());

        log.info("Editing User with id {}", id);
        return userRepository.save(editedUser);
    }

    @Override
    public User create(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null
                || userRepository.findByEmail(user.getEmail()) != null) return null;

        user.setAge(ageToString(user.getBirthDate()));
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        log.info("Saving new User: username = {}, email = {}, age = {}",
                user.getUsername(), user.getEmail(), user.getAge());
        return userRepository.save(user);
    }

    @Override
    public boolean isCourseOwner(long userId, long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        return course.getAuthor().getId() == userId;
    }

    @Override
    public boolean isCourseFollower(long followerId, Course course) {
        return course.getFollowers().stream()
                .anyMatch(user -> user.getId().equals(followerId));
    }

    @Override
    public boolean isCourseFavorite(long userId, Course course) {
        return course.getUsers().stream()
                .anyMatch(user -> user.getId().equals(userId));
    }

    @Override
    public boolean isNewsAuthor(long userId, long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));
        return news.getAuthor().getId() == userId;
    }

    public boolean isQuestionAuthor(long userId, long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return question.getAuthor().getId() == userId;
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting User with id {}", id);
        userRepository.deleteById(id);
    }

    private String ageToString(LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        int lastDigit = age % 10;
        if (lastDigit == 1 && age != 11) {
            return age + " год";
        } else if (lastDigit >= 2 && lastDigit <= 4 && !(age >= 12 && age <= 14)) {
            return age + " года";
        } else {
            return age + " лет";
        }
    }

}
