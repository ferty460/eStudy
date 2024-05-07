package com.example.estudy.service.dao.user;

import com.example.estudy.domain.course.Course;
import com.example.estudy.domain.user.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    User update(User user, Long id);

    User create(User user);

    boolean isCourseOwner(long userId, long courseId);

    boolean isCourseFollower(long followerId, Course course);

    boolean isCourseFavorite(long userId, Course course);

    boolean isNewsAuthor(long userId, long newsId);

    void delete(Long id);

}
