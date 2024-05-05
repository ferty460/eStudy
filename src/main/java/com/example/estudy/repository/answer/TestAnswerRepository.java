package com.example.estudy.repository.answer;

import com.example.estudy.domain.answer.TestAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestAnswerRepository extends JpaRepository<TestAnswer, Long> {

    TestAnswer findByTestIdAndUserId(Long testId, Long userId);

}
