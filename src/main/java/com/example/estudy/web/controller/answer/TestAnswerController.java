package com.example.estudy.web.controller.answer;

import com.example.estudy.domain.answer.TestAnswer;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.TestAnswerServiceImpl;
import com.example.estudy.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practical/test/answer")
@RequiredArgsConstructor
@Validated
public class TestAnswerController {

    private final TestAnswerServiceImpl answerService;
    private final UserServiceImpl userService;

    @PostMapping("/create")
    public void create(@RequestParam("userAnswer") Long userAnswer,
                       @RequestParam("test_id") Long testId,
                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getByUsername(userDetails.getUsername());
        TestAnswer answer = new TestAnswer();
        answer.setUserAnswer(userAnswer);
        answerService.create(answer, testId, user.getId());
    }

    @PostMapping("/update")
    public void update(@RequestParam("userAnswer") Long userAnswer,
                       @RequestParam("test_id") Long testId,
                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getByUsername(userDetails.getUsername());
        TestAnswer answer = answerService.getByTestIdAndUserId(testId, user.getId());
        answer.setUserAnswer(userAnswer);
        answerService.update(answer, answer.getId());
    }

}
