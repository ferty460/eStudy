package com.example.estudy.web.controller.answer;

import com.example.estudy.domain.answer.GapsAnswerRequest;
import com.example.estudy.domain.answer.GapsTaskAnswer;
import com.example.estudy.domain.user.User;
import com.example.estudy.service.impl.GapsTaskAnswerServiceImpl;
import com.example.estudy.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/practical/gaps/answer")
@RequiredArgsConstructor
@Validated
public class GapsTaskAnswerController {

    private final GapsTaskAnswerServiceImpl answerService;
    private final UserServiceImpl userService;

    @PostMapping("/create")
    public void create(@RequestBody GapsAnswerRequest request,
                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getByUsername(userDetails.getUsername());
        GapsTaskAnswer answer = new GapsTaskAnswer();
        Long gapsId = request.getGapsId();
        Map<String, String> userAnswer = request.getUserAnswer();
        answerService.create(answer, gapsId, user.getId(), userAnswer);
    }

    @PostMapping("/update")
    public void update(@RequestBody GapsAnswerRequest request,
                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getByUsername(userDetails.getUsername());
        Long gapsId = request.getGapsId();
        Map<String, String> userAnswer = request.getUserAnswer();
        GapsTaskAnswer answer = answerService.getByGapsTaskIdAndUserId(gapsId, user.getId());
        answerService.update(answer, answer.getId(), userAnswer);
    }

}
