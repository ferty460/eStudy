package com.example.estudy.web.controller.answer;

import com.example.estudy.domain.answer.SortTaskAnswer;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.SortTaskAnswerServiceImpl;
import com.example.estudy.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/practical/sort/answer")
@RequiredArgsConstructor
@Validated
public class SortTaskAnswerController {

    private final SortTaskAnswerServiceImpl answerService;
    private final UserServiceImpl userService;

    @PostMapping("/create")
    public void create(@RequestParam("userAnswer") List<String> userAnswer,
                       @RequestParam("sort_id") Long sortId,
                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getByUsername(userDetails.getUsername());
        SortTaskAnswer answer = new SortTaskAnswer();
        answerService.create(answer, sortId, user.getId(), userAnswer);
    }

    @PostMapping("/update")
    public void update(@RequestParam("userAnswer") List<String> userAnswer,
                       @RequestParam("sort_id") Long sortId,
                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getByUsername(userDetails.getUsername());
        SortTaskAnswer answer = answerService.getByTaskIdAndUserId(sortId, user.getId());
        answerService.update(answer, answer.getId(), userAnswer);
    }

}
