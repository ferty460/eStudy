package com.example.estudy.repository.answer;

import com.example.estudy.domain.answer.TextTaskAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextTaskAnswerRepository extends JpaRepository<TextTaskAnswer, Long> {

    TextTaskAnswer findByTextTaskIdAndUserId(Long taskId, Long userId);

}
