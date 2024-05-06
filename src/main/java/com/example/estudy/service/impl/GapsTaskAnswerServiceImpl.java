package com.example.estudy.service.impl;

import com.example.estudy.domain.answer.GapsTaskAnswer;
import com.example.estudy.domain.lesson.content.practical.GapsTask;
import com.example.estudy.domain.lesson.content.practical.GapsValueItem;
import com.example.estudy.repository.UserRepository;
import com.example.estudy.repository.answer.GapsTaskAnswerRepository;
import com.example.estudy.repository.practical.GapsTaskRepository;
import com.example.estudy.service.GapsTaskAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GapsTaskAnswerServiceImpl implements GapsTaskAnswerService {

    private final GapsTaskAnswerRepository answerRepository;
    private final GapsTaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public GapsTaskAnswer getById(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new RuntimeException("Gaps task answer not found"));
    }

    @Override
    public GapsTaskAnswer getByGapsTaskIdAndUserId(Long taskId, Long userId) {
        return answerRepository.findByGapsTaskIdAndUserId(taskId, userId);
    }

    public boolean taskIsTried(Long taskId, Long userId) {
        return answerRepository.findByGapsTaskIdAndUserId(taskId, userId) != null;
    }

    public boolean taskIsComplete(Long taskId, Long userId) {
        return answerRepository.findByGapsTaskIdAndUserId(taskId, userId) != null && answerRepository.findByGapsTaskIdAndUserId(taskId, userId).isCorrect();
    }

    @Override
    public GapsTaskAnswer create(GapsTaskAnswer answer, Long taskId, Long userId, Map<String, String> gaps) {
        GapsTask task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Gaps task not found"));
        Map<String, String> correctGaps = task.getValues().stream()
                .collect(Collectors.toMap(
                        valueItem -> valueItem.getId().toString(),
                        GapsValueItem::getValue
                ));

        answer.setGapsTask(task);
        answer.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        answer.setUserAnswer(mapToString(gaps));
        answer.setCorrectAnswer(mapToString(correctGaps));
        answer.setCorrect(mapToString(correctGaps).equals(mapToString(gaps)));

        log.info("Saving new Gaps task answer: taskId = {}, userId = {}", taskId, userId);
        return answerRepository.save(answer);
    }

    @Override
    public GapsTaskAnswer update(GapsTaskAnswer answer, Long answerId, Map<String, String> gaps) {
        GapsTaskAnswer editedAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Gaps task answer not found"));
        Map<String, String> correctGaps = editedAnswer.getGapsTask().getValues().stream()
                .collect(Collectors.toMap(
                        valueItem -> valueItem.getId().toString(),
                        GapsValueItem::getValue
                ));

        editedAnswer.setUserAnswer(mapToString(gaps));
        editedAnswer.setCorrect(mapToString(correctGaps).equals(mapToString(gaps)));

        log.info("Editing Gaps task answer with id {}", answerId);
        return answerRepository.save(editedAnswer);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Gaps task answer with id {}", id);
        answerRepository.deleteById(id);
    }

    private static String mapToString(Map<String, String> map) {
        return String.join(", ", map.values());
    }

}
