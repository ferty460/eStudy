package com.example.estudy.web.controller;

import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.UserServiceImpl;
import com.example.estudy.web.dto.user.UserDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Validated(OnCreate.class) UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userServiceImpl.create(user);
        return "redirect:/auth/login";
    }

}
