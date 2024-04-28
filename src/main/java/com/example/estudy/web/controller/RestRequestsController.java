package com.example.estudy.web.controller;

import com.example.estudy.service.impl.TextTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practical/text")
@RequiredArgsConstructor
public class RestRequestsController {

    private final TextTaskServiceImpl textTaskService;

    @GetMapping("/findTextTask")
    public String findById(@RequestParam("id") Long id) {
        return textTaskService.getById(id).getCorrectAnswer();
    }

}
