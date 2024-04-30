package com.example.estudy.web.controller;

import com.example.estudy.domain.lesson.content.practical.SortingTask;
import com.example.estudy.domain.lesson.content.practical.SortingTaskElement;
import com.example.estudy.service.impl.SortingTaskElementServiceImpl;
import com.example.estudy.service.impl.SortingTaskServiceImpl;
import com.example.estudy.service.impl.TestItemServiceImpl;
import com.example.estudy.service.impl.TextTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RestRequestsController {

    private final TextTaskServiceImpl textTaskService;
    private final TestItemServiceImpl itemService;
    private final SortingTaskElementServiceImpl sortingTaskElementService;
    private final SortingTaskServiceImpl sortingTaskService;

    @GetMapping("/practical/text/findTextTask")
    public String findById(@RequestParam("id") Long id) {
        return textTaskService.getById(id).getCorrectAnswer();
    }

    @GetMapping("/practical/test/findTestItem")
    public Long findByTestId(@RequestParam("id") Long id) {
        return itemService.getByRightAndTestId(id).getId();
    }

    @PostMapping("/practical/sort/update-position")
    public void updatePosition(@RequestParam("elementId") Long elementId, @RequestParam("position") int position) {
        sortingTaskElementService.updatePosition(elementId, position);
    }

    @GetMapping("/sorting-tasks/{taskId}/correct-order")
    public List<String> getCorrectOrder(@PathVariable Long taskId) {
        SortingTask sortingTask = sortingTaskService.getById(taskId);

        return sortingTask.getElements().stream()
                .sorted(Comparator.comparingInt(SortingTaskElement::getPosition))
                .map(SortingTaskElement::getContent)
                .collect(Collectors.toList());
    }

}
