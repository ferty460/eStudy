package com.example.estudy.web.controller;

import com.example.estudy.domain.course.Course;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.CourseService;
import com.example.estudy.service.UserService;
import com.example.estudy.web.dto.course.CourseDto;
import com.example.estudy.web.dto.user.UserDto;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.CourseMapper;
import com.example.estudy.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final CourseService courseService;

    private final UserMapper userMapper;
    private final CourseMapper courseMapper;

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @PutMapping
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto, Long userId) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.update(user, userId);
        return userMapper.toDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/courses")
    public List<CourseDto> getCoursesByUserId(@PathVariable Long id) {
        List<Course> courses = courseService.getAllByUserId(id);
        return courseMapper.toDto(courses);
    }

}
