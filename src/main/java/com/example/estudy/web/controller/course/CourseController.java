package com.example.estudy.web.controller.course;

import com.example.estudy.domain.course.Availability;
import com.example.estudy.domain.course.Course;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.course.CourseServiceImpl;
import com.example.estudy.service.impl.course.ModuleServiceImpl;
import com.example.estudy.service.impl.course.TagServiceImpl;
import com.example.estudy.service.impl.user.UserServiceImpl;
import com.example.estudy.web.dto.course.CourseDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
@Validated
public class CourseController {

    private final CourseServiceImpl courseService;
    private final UserServiceImpl userService;
    private final TagServiceImpl tagService;
    private final ModuleServiceImpl moduleService;

    private final CourseMapper courseMapper;

    @GetMapping("/catalog")
    public String catalog(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "s", required = false) String sort, Model model
    ) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
        }

        List<Course> courses = courseService.getCourses(tag, query, sort, Availability.PUBLIC);

        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("courses", courses);
        return "catalog";
    }

    @GetMapping
    @PreAuthorize("@courseServiceImpl.isPrivate(#id) ? @courseServiceImpl.isValidAccessCode(#id, #accessCode) " +
            "|| @userServiceImpl.isCourseOwner(authentication.principal.id, #id)" +
            "|| @userServiceImpl.isCourseFollower(authentication.principal.id, @courseServiceImpl.getById(#id))" +
            "|| @userServiceImpl.isCourseFavorite(authentication.principal.id, @courseServiceImpl.getById(#id)) : true")
    public String getById(@RequestParam("id") Long id, @RequestParam(value = "access_code", required = false) String accessCode,
                          @AuthenticationPrincipal UserDetails userDetails, Model model) {
        Course course = courseService.getById(id);
        User user = userService.getByUsername(userDetails.getUsername());

        model.addAttribute("course", course);
        model.addAttribute("user", user);
        model.addAttribute("modules", moduleService.getAllByCourseId(course.getId()));
        model.addAttribute("isPrivate", courseService.isPrivate(course.getId()));
        model.addAttribute("isCourseOwner", userService.isCourseOwner(user.getId(), course.getId()));
        model.addAttribute("isCourseFollower", userService.isCourseFollower(user.getId(), course));
        model.addAttribute("isCourseFavorite", userService.isCourseFavorite(user.getId(), course));
        model.addAttribute("followed_courses", user.getFollowedCourses());

        return "course";
    }

    @GetMapping("/l")
    @PreAuthorize("@userServiceImpl.isCourseFollower(authentication.principal.id, @courseServiceImpl.getById(#id)) " +
            "|| @userServiceImpl.isCourseOwner(authentication.principal.id, #id)")
    public String learning_process(@RequestParam("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        Course course = courseService.getById(id);
        User user = userService.getByUsername(userDetails.getUsername());
        model.addAttribute("course", course);
        model.addAttribute("modules", course.getModules());
        model.addAttribute("user", user);
        model.addAttribute("isCourseOwner", userService.isCourseOwner(user.getId(), course.getId()));
        model.addAttribute("followed_courses", user.getFollowedCourses());
        return "learning_process";
    }

    @PostMapping("/follow")
    public String follow(@AuthenticationPrincipal UserDetails userDetails, Long courseId) {
        User user = userService.getByUsername(userDetails.getUsername());
        Course course = courseService.getById(courseId);
        courseService.addFollowerToCourse(user, course);
        return "redirect:/courses?id=" + courseId;
    }

    @PostMapping("/favorite")
    public String favorite(@AuthenticationPrincipal UserDetails userDetails, Long courseId) {
        User user = userService.getByUsername(userDetails.getUsername());
        Course course = courseService.getById(courseId);
        courseService.addUserToCourse(user, course);
        return "redirect:/courses?id=" + courseId;
    }

    @GetMapping("/create")
    public String createPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("tags", tagService.getAll());
        model.addAttribute("my_courses", courseService.getAllByUserId(user.getId()));
        model.addAttribute("followed_courses", user.getFollowedCourses());
        return "add_course";
    }

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) CourseDto courseDto,
                         @AuthenticationPrincipal UserDetails userDetails,
                         @RequestParam("file") MultipartFile file) throws IOException {
        Course course = courseMapper.toEntity(courseDto);
        User user = userService.getByUsername(userDetails.getUsername());
        courseService.create(course, user.getId(), file);
        return "redirect:/profile";
    }

    @PostMapping("/rate")
    public String rate(@RequestParam("rating") String ratingStr, @RequestParam("courseId") Long courseId, @AuthenticationPrincipal UserDetails userDetails) {
        int rating = Integer.parseInt(ratingStr); // Преобразование строки в число
        User user = userService.getByUsername(userDetails.getUsername());
        courseService.rate((float) rating, courseId, user.getId());
        return "redirect:/courses?id=" + courseId;
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@courseServiceImpl.getById(#courseDto.id).author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) CourseDto courseDto,
                         @RequestParam("file") MultipartFile file) throws IOException {
        Course course = courseMapper.toEntity(courseDto);
        Course updatedCourse = courseService.update(course, course.getId(), file);
        return "redirect:/courses?id=" + updatedCourse.getId();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@courseServiceImpl.getById(#id).author.id == authentication.principal.id")
    public String deleteById(@PathVariable Long id) {
        courseService.delete(id);
        return "redirect:/profile";
    }

}
