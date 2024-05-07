package com.example.estudy.service.impl.course.content.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;
import com.example.estudy.repository.course.content.theoretical.ChapterRepository;
import com.example.estudy.repository.course.content.TheoreticalContentRepository;
import com.example.estudy.service.dao.course.content.theoretical.ChapterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final TheoreticalContentRepository contentRepository;

    @Override
    public Chapter getById(Long id) {
        return chapterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));
    }

    @Override
    public List<Chapter> getAllByTheoreticalContentId(Long contentId) {
        return chapterRepository.findAllByTheoreticalContent_Id(contentId);
    }

    @Override
    public Chapter create(Chapter chapter, Long contentId) {
        chapter.setTheoreticalContent(contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Theoretical content not found")));

        log.info("Saving new Chapter: name = {}, contentId = {}", chapter.getName(), chapter.getTheoreticalContent().getId());
        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter update(Chapter chapter, Long chapterId) {
        Chapter editedChapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));
        editedChapter.setName(chapter.getName());

        log.info("Editing Chapter with id {}", editedChapter.getId());
        return chapterRepository.save(editedChapter);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Chapter with id {}", id);
        chapterRepository.deleteById(id);
    }

}
