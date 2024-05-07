package com.example.estudy.service.impl.course.content.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Title;
import com.example.estudy.repository.course.content.theoretical.ChapterRepository;
import com.example.estudy.repository.course.content.theoretical.TitleRepository;
import com.example.estudy.service.dao.course.content.theoretical.TitleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TitleServiceImpl implements TitleService {

    private final TitleRepository titleRepository;
    private final ChapterRepository chapterRepository;

    @Override
    public Title getById(Long id) {
        return titleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Title not found"));
    }

    @Override
    public List<Title> getAllByChapterId(Long id) {
        return titleRepository.findAllByChapter_Id(id);
    }

    @Override
    public Title create(Title title, Long chapterId) {
        title.setChapter(chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found")));

        log.info("Saving new Title: value = {}, chapterId = {}", title.getValue(), title.getChapter().getId());
        return titleRepository.save(title);
    }

    @Override
    public Title update(Title title, Long titleId) {
        Title editedTitle = titleRepository.findById(titleId)
                .orElseThrow(() -> new RuntimeException("Title not found"));
        editedTitle.setValue(title.getValue());

        log.info("Editing title with id {}", titleId);
        return titleRepository.save(editedTitle);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Title with id {}", id);
        titleRepository.deleteById(id);
    }

}
