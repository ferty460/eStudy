package com.example.estudy.service.impl;

import com.example.estudy.domain.answer.SortTaskAnswer;
import com.example.estudy.domain.lesson.content.practical.SortingTask;
import com.example.estudy.domain.lesson.content.practical.SortingTaskElement;
import com.example.estudy.repository.UserRepository;
import com.example.estudy.repository.answer.SortTaskAnswerRepository;
import com.example.estudy.repository.practical.SortingTaskRepository;
import com.example.estudy.service.SortTaskAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SortTaskAnswerServiceImpl implements SortTaskAnswerService {

    private final SortTaskAnswerRepository answerRepository;
    private final SortingTaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public SortTaskAnswer getById(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new RuntimeException("Sort task answer not found"));
    }

    public boolean taskIsTried(Long taskId, Long userId) {
        return answerRepository.findBySortTaskIdAndUserId(taskId, userId) != null;
    }

    public boolean taskIsComplete(Long taskId, Long userId) {
        return answerRepository.findBySortTaskIdAndUserId(taskId, userId) != null && answerRepository.findBySortTaskIdAndUserId(taskId, userId).isCorrect();
    }

    public SortTaskAnswer getByTaskIdAndUserId(Long taskId, Long userId) {
        if (answerRepository.findBySortTaskIdAndUserId(taskId, userId) == null) return null;
        return answerRepository.findBySortTaskIdAndUserId(taskId, userId);
    }

    @Override
    public SortTaskAnswer create(SortTaskAnswer answer, Long taskId, Long userId, List<String> order) {
        SortingTask task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Sort task not found"));
        List<String> positions = task.getElements().stream()
                .sorted(Comparator.comparingInt(SortingTaskElement::getPosition))
                .map(SortingTaskElement::getContent)
                .toList();

        answer.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        answer.setSortTask(task);
        answer.setUserAnswer(listToString(order));
        answer.setCorrectAnswer(listToString(positions));
        answer.setCorrect(listToString(positions).equals(listToString(order)));

        log.info("Saving new Sort task answer: taskId = {}, userId = {}", taskId, userId);
        return answerRepository.save(answer);
    }

    @Override
    public SortTaskAnswer update(SortTaskAnswer answer, Long answerId, List<String> order) {
        SortTaskAnswer editedAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Sort task answer not found"));
        SortingTask task = taskRepository.findById(editedAnswer.getSortTask().getId())
                .orElseThrow(() -> new RuntimeException("Sort task not found"));
        List<String> positions = task.getElements().stream()
                .sorted(Comparator.comparingInt(SortingTaskElement::getPosition))
                .map(SortingTaskElement::getContent)
                .toList();

        editedAnswer.setUserAnswer(listToString(order));
        editedAnswer.setCorrect(listToString(positions).equals(listToString(order)));

        log.info("Editing Sort task answer with id {}", answerId);
        return answerRepository.save(editedAnswer);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Sort task answer with id {}", id);
        answerRepository.deleteById(id);
    }

    private String listToString(List<String> list) {
        return String.join(",", list).replaceAll("[\\[\\]\"]", "");
    }

    private List<String> stringToList(String str) {
        return Arrays.asList(str.split(","));
    }

}
