package com.example.estudy.web.controller.course.lesson.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.domain.lesson.content.practical.Test;
import com.example.estudy.service.impl.PracticalContentServiceImpl;
import com.example.estudy.service.impl.TestServiceImpl;
import com.example.estudy.web.dto.lesson.TestDto;
import com.example.estudy.web.dto.validation.OnUpdate;
import com.example.estudy.web.mappers.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practical/test")
@RequiredArgsConstructor
@Validated
public class TestController {

    private final TestServiceImpl testService;
    private final PracticalContentServiceImpl practicalContentService;

    private final TestMapper testMapper;

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || " +
            "@testServiceImpl.getById(#testDto.id).practicalContent.lesson.module.course.author.id == authentication.principal.id")
    public String update(@Validated(OnUpdate.class) TestDto testDto, Long answer) {
        Test test = testMapper.toEntity(testDto);
        Test editedTest = testService.update(test, test.getId(), answer);

        PracticalContent content = new PracticalContent();
        content.setTitle(test.getTitle());
        content.setDescription(test.getDescription());
        practicalContentService.update(content, editedTest.getPracticalContent().getId());

        return "redirect:/practical?id=" + editedTest.getPracticalContent().getId();
    }

}
