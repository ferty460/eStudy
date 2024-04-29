package com.example.estudy.web.controller;

import com.example.estudy.service.impl.TestItemServiceImpl;
import com.example.estudy.service.impl.TextTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestRequestsController {

    private final TextTaskServiceImpl textTaskService;
    private final TestItemServiceImpl itemService;

    @GetMapping("/practical/text/findTextTask")
    public String findById(@RequestParam("id") Long id) {
        return textTaskService.getById(id).getCorrectAnswer();
    }

    @GetMapping("/practical/test/findTestItem")
    public Long findByTestId(@RequestParam("id") Long id) {
        return itemService.getByRightAndTestId(id).getId();
    }

}
