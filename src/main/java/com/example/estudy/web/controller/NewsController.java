package com.example.estudy.web.controller;

import com.example.estudy.domain.news.News;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.NewsItemServiceImpl;
import com.example.estudy.service.impl.NewsServiceImpl;
import com.example.estudy.service.impl.UserServiceImpl;
import com.example.estudy.web.dto.news.NewsDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.NewsMapper;
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
@RequiredArgsConstructor
@RequestMapping("/news")
@Validated
public class NewsController {

    private final UserServiceImpl userService;
    private final NewsServiceImpl newsService;
    private final NewsItemServiceImpl newsItemService;

    private final NewsMapper newsMapper;

    @GetMapping
    public String newsPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
        }
        model.addAttribute("news", newsService.getAll());
        return "news";
    }

    @GetMapping("/read")
    public String readNews(@RequestParam("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
            model.addAttribute("isNewsAuthor", userService.isNewsAuthor(user.getId(), id));
        }
        News news = newsService.getById(id);
        model.addAttribute("news", news);
        model.addAttribute("notes", newsItemService.getAllByNewsId(news.getId()));
        return "read_news";
    }

    @GetMapping("/create")
    public String createPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            User user = userService.getByUsername(userDetails.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("followed_courses", user.getFollowedCourses());
        }
        return "add_news";
    }

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) NewsDto newsDto,
                         @AuthenticationPrincipal UserDetails userDetails,
                         @RequestParam("file") MultipartFile file) throws IOException {
        News news = newsMapper.toEntity(newsDto);
        User user = userService.getByUsername(userDetails.getUsername());
        newsService.create(news, user.getId(), file);
        return "redirect:/news/read?id=" + news.getId();
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@newsServiceImpl.getById(#newsDto.id).author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) NewsDto newsDto, Long id,
                         @RequestParam("file") MultipartFile file) throws IOException {
        News news = newsMapper.toEntity(newsDto);
        News updatedNews = newsService.update(news, id, file);
        return "redirect:/news/read?id=" + updatedNews.getId();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@newsServiceImpl.getById(#id).author.id == authentication.principal.id")
    public String delete(@RequestParam("id") Long id) {
        newsItemService.deleteAllByNewsId(id);
        newsService.delete(id);
        return "redirect:/news";
    }

}
