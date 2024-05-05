package com.example.estudy.repository.answer;

import com.example.estudy.domain.answer.SortTaskAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortTaskAnswerRepository extends JpaRepository<SortTaskAnswer, Long> {

    SortTaskAnswer findBySortTaskIdAndUserId(Long taskId, Long userId);

}
