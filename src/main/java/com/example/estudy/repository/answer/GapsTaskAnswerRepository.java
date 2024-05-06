package com.example.estudy.repository.answer;

import com.example.estudy.domain.answer.GapsTaskAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GapsTaskAnswerRepository extends JpaRepository<GapsTaskAnswer, Long> {

    GapsTaskAnswer findByGapsTaskIdAndUserId(Long taskId, Long userId);

}
