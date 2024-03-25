package com.example.estudy.service;

import com.example.estudy.domain.user.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    User update(User user, Long id);

    User create(User user);

    boolean isCourseOwner(long userId, long courseId);

    void delete(Long id);

}
