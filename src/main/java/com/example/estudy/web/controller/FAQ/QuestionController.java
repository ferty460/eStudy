package com.example.estudy.web.controller.FAQ;

import com.example.estudy.domain.FAQ.Question;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.FAQ.QuestionItemServiceImpl;
import com.example.estudy.service.impl.FAQ.QuestionServiceImpl;
import com.example.estudy.service.impl.user.UserServiceImpl;
import com.example.estudy.web.dto.FAQ.QuestionDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.QuestionMapper;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/faq")
@RequiredArgsConstructor
@Validated
public class QuestionController {

    private final QuestionServiceImpl questionService;
    private final QuestionItemServiceImpl itemService;
    private final UserServiceImpl userService;

    private final QuestionMapper questionMapper;

    @GetMapping
    public String faqPage(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpSession session) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
        }
        model.addAttribute("questions", questionService.getAll());
        model.addAttribute("theme", tempToggleTheme(session));
        return "support";
    }

    @GetMapping("/read")
    public String readNews(@RequestParam("id") Long id, @AuthenticationPrincipal UserDetails userDetails,
                           Model model, HttpSession session) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
            model.addAttribute("isQuestionAuthor", userService.isQuestionAuthor(user.getId(), id));
        }
        Question question = questionService.getById(id);
        model.addAttribute("questions", question);
        model.addAttribute("items", itemService.getAllByQuestionId(question.getId()));
        model.addAttribute("theme", tempToggleTheme(session));
        return "read_question";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODER')")
    public String createPage(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpSession session) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
        }
        model.addAttribute("theme", tempToggleTheme(session));
        return "add_question";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODER')")
    public String create(@Validated(OnCreate.class) QuestionDto questionDto,
                         @AuthenticationPrincipal UserDetails userDetails,
                         @RequestParam("file") MultipartFile file) throws IOException {
        Question question = questionMapper.toEntity(questionDto);
        User user = userService.getByUsername(userDetails.getUsername());
        questionService.create(question, user.getId(), file);
        return "redirect:/faq/read?id=" + question.getId();
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODER')")
    public String update(@Validated(OnUpdate.class) QuestionDto questionDto, Long id,
                         @RequestParam("file") MultipartFile file) throws IOException {
        Question question = questionMapper.toEntity(questionDto);
        Question updatedQuestion = questionService.update(question, id, file);
        return "redirect:/faq/read?id=" + updatedQuestion.getId();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODER')")
    public String delete(@RequestParam("id") Long id) {
        itemService.deleteAllByQuestionId(id);
        questionService.delete(id);
        return "redirect:/faq";
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
