package com.example.estudy.service.dao.FAQ;

import com.example.estudy.domain.FAQ.Question;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    Question getById(Long id);

    List<Question> getAll();

    Question create(Question question, Long userId, MultipartFile file) throws IOException;

    Question update(Question question, Long questionId, MultipartFile file) throws IOException;

    void delete(Long id);

}
