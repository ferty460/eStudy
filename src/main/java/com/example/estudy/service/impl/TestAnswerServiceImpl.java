package com.example.estudy.service.impl;

import com.example.estudy.domain.answer.TestAnswer;
import com.example.estudy.domain.lesson.content.practical.Test;
import com.example.estudy.domain.lesson.content.practical.TestItem;
import com.example.estudy.repository.UserRepository;
import com.example.estudy.repository.answer.TestAnswerRepository;
import com.example.estudy.repository.practical.TestRepository;
import com.example.estudy.service.TestAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestAnswerServiceImpl implements TestAnswerService {

    private final TestAnswerRepository answerRepository;
    private final TestRepository testRepository;
    private final UserRepository userRepository;

    @Override
    public TestAnswer getById(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new RuntimeException("Test answer not found"));
    }

    public boolean taskIsTried(Long taskId, Long userId) {
        return answerRepository.findByTestIdAndUserId(taskId, userId) != null;
    }

    public boolean taskIsComplete(Long taskId, Long userId) {
        return answerRepository.findByTestIdAndUserId(taskId, userId) != null && answerRepository.findByTestIdAndUserId(taskId, userId).isCorrect();
    }

    public TestAnswer getByTestIdAndUserId(Long taskId, Long userId) {
        if (answerRepository.findByTestIdAndUserId(taskId, userId) == null) return null;
        return answerRepository.findByTestIdAndUserId(taskId, userId);
    }

    @Override
    public TestAnswer create(TestAnswer answer, Long testId, Long userId) {
        Test test = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
        answer.setTest(test);
        answer.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        Optional<TestItem> correctAnswer = test.getItems().stream()
                .filter(TestItem::isRight)
                .findFirst();
        correctAnswer.ifPresent(item -> answer.setCorrectAnswer(item.getId()));
        answer.setCorrect(answer.getCorrectAnswer().equals(answer.getUserAnswer()));

        log.info("Saving new Test answer: testId = {}, userId = {}", testId, userId);
        return answerRepository.save(answer);
    }

    @Override
    public TestAnswer update(TestAnswer answer, Long answerId) {
        TestAnswer editedAnswer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Test answer not found"));
        editedAnswer.setUserAnswer(answer.getUserAnswer());
        editedAnswer.setCorrect(answer.getUserAnswer().equals(editedAnswer.getCorrectAnswer()));

        log.info("Editing Test answer with id {}", answerId);
        return answerRepository.save(editedAnswer);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting test answer with id {}", id);
        answerRepository.deleteById(id);
    }

}
