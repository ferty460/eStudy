package com.example.estudy.service.impl.course.content.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Text;
import com.example.estudy.repository.course.content.theoretical.ChapterRepository;
import com.example.estudy.repository.course.content.theoretical.TextRepository;
import com.example.estudy.service.dao.course.content.theoretical.TextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TextServiceImpl implements TextService {

    private final TextRepository textRepository;
    private final ChapterRepository chapterRepository;

    @Override
    public Text getById(Long id) {
        return textRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Text not found"));
    }

    @Override
    public List<Text> getAllByChapterId(Long id) {
        return textRepository.findAllByChapter_Id(id);
    }

    @Override
    public Text create(Text text, Long chapterId) {
        text.setChapter(chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found")));

        log.info("Saving new Text: chapterId = {}", chapterId);
        return textRepository.save(text);
    }

    @Override
    public Text update(Text text, Long textId) {
        Text editedText = textRepository.findById(textId)
                .orElseThrow(() -> new RuntimeException("Text not found"));
        editedText.setValue(text.getValue());

        log.info("Editing Text with id {}", textId);
        return textRepository.save(editedText);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Text with id {}", id);
        textRepository.deleteById(id);
    }

}
