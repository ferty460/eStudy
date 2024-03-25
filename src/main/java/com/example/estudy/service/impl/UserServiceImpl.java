package com.example.estudy.service.impl;

import com.example.estudy.domain.course.Course;
import com.example.estudy.domain.user.User;
import com.example.estudy.repository.CourseRepository;
import com.example.estudy.repository.UserRepository;
import com.example.estudy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username " + username + " not found"));
    }

    @Override
    public User update(User user, Long id) {
        User editedUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        editedUser.setName(user.getName());
        editedUser.setSurname(user.getSurname());
        editedUser.setPatronymic(user.getPatronymic());
        editedUser.setUsername(user.getUsername());
        editedUser.setEmail(user.getEmail());
        return null;
    }

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean isCourseOwner(long userId, long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found"));
        return course.getAuthor().getId() == userId;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
