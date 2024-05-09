package com.example.estudy.web.controller.course;

import com.example.estudy.domain.course.Tag;
import com.example.estudy.service.impl.course.TagServiceImpl;
import com.example.estudy.web.dto.course.TagDto;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.mappers.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags")
@RequiredArgsConstructor
@Validated
public class TagController {

    private final TagServiceImpl tagService;

    private final TagMapper tagMapper;

    @PostMapping("/create")
    public String create(@Validated(OnCreate.class) TagDto tagDto) {
        Tag tag = tagMapper.toEntity(tagDto);
        tagService.create(tag);

        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String update(@Validated(OnCreate.class) TagDto tagDto) {
        Tag tag = tagMapper.toEntity(tagDto);
        tagService.update(tag, tag.getId());

        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String delete(Long id) {
        tagService.delete(id);

        return "redirect:/admin";
    }

}
