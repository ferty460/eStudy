package com.example.estudy.web.controller;

import com.example.estudy.domain.course.Course;
import com.example.estudy.domain.user.Role;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.dao.course.CourseService;
import com.example.estudy.service.dao.user.UserService;
import com.example.estudy.web.dto.course.CourseDto;
import com.example.estudy.web.dto.user.UserDto;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.CourseMapper;
import com.example.estudy.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @PostMapping("/update")
    public String update(@Validated(OnUpdate.class) UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userService.update(user, user.getId());
        return "redirect:/profile";
    }

    @PostMapping("/updateRoles")
    public String updateRoles(
            @RequestParam(required = false) boolean roleUser,
            @RequestParam(required = false) boolean roleModerator,
            @RequestParam(required = false) boolean roleAdmin,
            @RequestParam("id") Long id
    ) {
        Set<Role> roles = new HashSet<>();
        if (roleUser) {
            roles.add(Role.ROLE_USER);
        }
        if (roleModerator) {
            roles.add(Role.ROLE_MODER);
        }
        if (roleAdmin) {
            roles.add(Role.ROLE_ADMIN);
        }
        userService.updateRoles(roles, id);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteById(Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/courses")
    public List<CourseDto> getCoursesByUserId(@PathVariable Long id) {
        List<Course> courses = courseService.getAllByUserId(id);
        return courseMapper.toDto(courses);
    }

}
