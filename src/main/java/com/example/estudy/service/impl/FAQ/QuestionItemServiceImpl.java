package com.example.estudy.service.impl.FAQ;

import com.example.estudy.domain.FAQ.QuestionItem;
import com.example.estudy.repository.FAQ.QuestionItemRepository;
import com.example.estudy.repository.FAQ.QuestionRepository;
import com.example.estudy.service.dao.FAQ.QuestionItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionItemServiceImpl implements QuestionItemService {

    private final QuestionItemRepository questionItemRepository;
    private final QuestionRepository questionRepository;

    @Override
    public QuestionItem getById(Long id) {
        return questionItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Question item not found"));
    }

    @Override
    public List<QuestionItem> getAll() {
        return questionItemRepository.findAll();
    }

    @Override
    public List<QuestionItem> getAllByQuestionId(Long id) {
        return questionItemRepository.findAllByQuestionIdOrderByDateOfCreated(id);
    }

    @Override
    public QuestionItem update(QuestionItem item, Long itemId) {
        QuestionItem editedItem = questionItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Question Item not found"));
        editedItem.setTitle(item.getTitle());
        editedItem.setText(item.getText());

        log.info("Editing Question Item with id {}", itemId);
        return questionItemRepository.save(editedItem);
    }

    @Override
    public QuestionItem create(QuestionItem item, Long newsId) {
        item.setQuestion(questionRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("Question not found")));

        log.info("Saving new News Item: title = {}, newsId = {}, authorId = {}",
                item.getTitle(), item.getQuestion().getId(), item.getQuestion().getAuthor().getId());
        return questionItemRepository.save(item);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting News Item with id {}", id);
        questionItemRepository.deleteById(id);
    }

    @Override
    public void deleteAllByQuestionId(Long id) {
        List<QuestionItem> items = questionItemRepository.findAllByQuestionIdOrderByDateOfCreated(id);
        for (QuestionItem item : items) {
            questionItemRepository.deleteById(item.getId());
        }
        log.info("Deleting Question Items from News with id {}", id);
    }

}
