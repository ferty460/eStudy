package com.example.estudy.web.controller.course.lesson;

import com.example.estudy.domain.module.Module;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.course.LessonServiceImpl;
import com.example.estudy.service.impl.course.ModuleServiceImpl;
import com.example.estudy.service.impl.user.UserServiceImpl;
import com.example.estudy.web.dto.module.ModuleDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.ModuleMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/modules")
@RequiredArgsConstructor
@Validated
public class ModuleController {

    private final ModuleServiceImpl moduleService;
    private final UserServiceImpl userService;
    private final LessonServiceImpl lessonService;

    private final ModuleMapper moduleMapper;

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) ModuleDto moduleDto, Long courseId) {
        Module module = moduleMapper.toEntity(moduleDto);
        moduleService.create(module, courseId);
        return "redirect:/modules?id=" + module.getId();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || @moduleServiceImpl.getById(#id).course.author.id == authentication.principal.id")
    public String getById(@RequestParam("id") Long id, @AuthenticationPrincipal UserDetails userDetails,
                          Model model, HttpSession session) {
        User user = userService.getByUsername(userDetails.getUsername());
        Module module = moduleService.getById(id);
        model.addAttribute("module1", module);
        model.addAttribute("lessons", lessonService.getAllByModuleId(module.getId()));
        model.addAttribute("user", user);
        model.addAttribute("followed_courses", user.getFollowedCourses());
        model.addAttribute("theme", tempToggleTheme(session));
        return "module";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || @moduleServiceImpl.getById(#moduleDto.id).course.author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) ModuleDto moduleDto) {
        Module module = moduleMapper.toEntity(moduleDto);
        moduleService.update(module, module.getId());
        return "redirect:/modules?id=" + module.getId();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') || @moduleServiceImpl.getById(#id).course.author.id == authentication.principal.id")
    public String delete(Long id) {
        Long courseId = moduleService.getById(id).getCourse().getId();
        moduleService.delete(id);
        return "redirect:/courses?id=" + courseId;
    }

    public String tempToggleTheme(HttpSession session) {
        String theme = (String) session.getAttribute("theme");
        if (theme == null) {
            theme = "light";
            session.setAttribute("theme", theme);
        }
        return theme;
    }

}
