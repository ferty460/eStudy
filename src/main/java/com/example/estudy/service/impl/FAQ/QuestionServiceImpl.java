package com.example.estudy.service.impl.FAQ;

import com.example.estudy.domain.FAQ.Question;
import com.example.estudy.domain.FAQ.QuestionImage;
import com.example.estudy.repository.FAQ.QuestionImageRepository;
import com.example.estudy.repository.FAQ.QuestionRepository;
import com.example.estudy.repository.user.UserRepository;
import com.example.estudy.service.dao.FAQ.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final QuestionImageRepository imageRepository;

    @Override
    public Question getById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public Question create(Question question, Long userId, MultipartFile file) throws IOException {
        question.setAuthor(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        if (file.getSize() != 0) {
            QuestionImage image = toImageEntity(file);
            question.setImage(image);
        }

        log.info("Saving new Question: authorId = {}, title = {}", userId, question.getTitle());
        return questionRepository.save(question);
    }

    @Override
    @Transactional
    public Question update(Question question, Long questionId, MultipartFile file) throws IOException {
        Question editedQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        imageRepository.deleteById(editedQuestion.getImage().getId());
        log.info("Image with id {} was deleted", editedQuestion.getImage().getId());

        editedQuestion.setTitle(question.getTitle());
        editedQuestion.setDescription(question.getDescription());
        if (file.getSize() != 0) {
            QuestionImage image = toImageEntity(file);
            editedQuestion.setImage(image);
        }

        editedQuestion = questionRepository.save(editedQuestion);

        log.info("Editing Question with id {}", questionId);
        return editedQuestion;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        imageRepository.deleteById(question.getImage().getId());
        log.info("Image with id {} was deleted", question.getImage().getId());

        log.info("Deleting Question with id {}", id);
        questionRepository.deleteById(id);
    }

    /* --------- IMAGE --------- */

    private QuestionImage toImageEntity(MultipartFile file) throws IOException {
        QuestionImage image = new QuestionImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());

        return image;
    }

}
