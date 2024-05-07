package com.example.estudy.repository.FAQ;

import com.example.estudy.domain.FAQ.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
