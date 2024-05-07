package com.example.estudy.repository.FAQ;

import com.example.estudy.domain.FAQ.QuestionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionItemRepository extends JpaRepository<QuestionItem, Long> {

    List<QuestionItem> findAllByQuestionIdOrderByDateOfCreated(Long id);

}
