package com.example.estudy.service.impl.answer;

import com.example.estudy.domain.answer.TextTaskAnswer;
import com.example.estudy.domain.lesson.content.practical.TextTask;
import com.example.estudy.repository.user.UserRepository;
import com.example.estudy.repository.answer.TextTaskAnswerRepository;
import com.example.estudy.repository.course.content.practical.TextTaskRepository;
import com.example.estudy.service.dao.answer.TextTaskAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TextTaskAnswerServiceImpl implements TextTaskAnswerService {

    private final TextTaskAnswerRepository answerRepository;
    private final TextTaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TextTaskAnswer getById(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new RuntimeException("Text task answer not found"));
    }

    public boolean taskIsTried(Long taskId, Long userId) {
        return answerRepository.findByTextTaskIdAndUserId(taskId, userId) != null;
    }

    public boolean taskIsComplete(Long taskId, Long userId) {
        return answerRepository.findByTextTaskIdAndUserId(taskId, userId) != null && answerRepository.findByTextTaskIdAndUserId(taskId, userId).isCorrect();
    }

    public TextTaskAnswer getByTaskIdAndUserId(Long taskId, Long userId) {
        if (answerRepository.findByTextTaskIdAndUserId(taskId, userId) == null) return null;
        return answerRepository.findByTextTaskIdAndUserId(taskId, userId);
    }

    @Override
    public TextTaskAnswer create(TextTaskAnswer answer, Long taskId, Long userId) {
        TextTask task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Text task not found"));
        answer.setTextTask(task);
        answer.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        answer.setCorrectAnswer(task.getCorrectAnswer());
        answer.setCorrect(answer.getUserAnswer().equals(task.getCorrectAnswer()));

        log.info("Saving new Text task answer: userId = {}, taskId = {}", userId, taskId);
        return answerRepository.save(answer);
    }

    @Override
    public TextTaskAnswer update(TextTaskAnswer answer, Long taskId) {
        TextTaskAnswer editedAnswer = answerRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Text task answer not found"));
        editedAnswer.setUserAnswer(answer.getUserAnswer());
        editedAnswer.setCorrect(answer.getUserAnswer().equals(editedAnswer.getCorrectAnswer()));

        log.info("Editing Text task answer with id {}", taskId);
        return answerRepository.save(editedAnswer);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Text task answer with id {}", id);
        answerRepository.deleteById(id);
    }

}
