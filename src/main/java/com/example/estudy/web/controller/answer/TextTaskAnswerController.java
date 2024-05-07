package com.example.estudy.web.controller.answer;

import com.example.estudy.domain.answer.TextTaskAnswer;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.answer.TextTaskAnswerServiceImpl;
import com.example.estudy.service.impl.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practical/text/answer")
@RequiredArgsConstructor
@Validated
public class TextTaskAnswerController {

    private final TextTaskAnswerServiceImpl answerService;
    private final UserServiceImpl userService;

    @PostMapping("/create")
    public void create(@RequestParam("userAnswer") String userAnswer,
                       @RequestParam("text_id") Long textId,
                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getByUsername(userDetails.getUsername());
        TextTaskAnswer answer = new TextTaskAnswer();
        answer.setUserAnswer(userAnswer);
        answerService.create(answer, textId, user.getId());
    }

    @PostMapping("/update")
    public void update(@RequestParam("userAnswer") String userAnswer,
                       @RequestParam("text_id") Long textId,
                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getByUsername(userDetails.getUsername());
        TextTaskAnswer answer = answerService.getByTaskIdAndUserId(textId, user.getId());
        answer.setUserAnswer(userAnswer);
        answerService.update(answer, answer.getId());
    }

}
