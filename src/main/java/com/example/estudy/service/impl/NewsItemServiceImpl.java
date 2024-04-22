package com.example.estudy.service.impl;

import com.example.estudy.domain.news.NewsItem;
import com.example.estudy.repository.NewsItemRepository;
import com.example.estudy.repository.NewsRepository;
import com.example.estudy.service.NewsItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsItemServiceImpl implements NewsItemService {

    private final NewsItemRepository newsItemRepository;
    private final NewsRepository newsRepository;

    @Override
    public NewsItem getById(Long id) {
        return newsItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @Override
    public List<NewsItem> getAll() {
        return newsItemRepository.findAll();
    }

    @Override
    public List<NewsItem> getAllByNewsId(Long id) {
        return newsItemRepository.findAllByNews_IdOrderByDateOfCreated(id);
    }

    @Override
    public NewsItem update(NewsItem item, Long itemId) {
        NewsItem editedItem = newsItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        editedItem.setTitle(item.getTitle());
        editedItem.setText(item.getText());

        log.info("Editing News Item with id {}", itemId);
        return newsItemRepository.save(editedItem);
    }

    @Override
    public NewsItem create(NewsItem item, Long newsId) {
        item.setNews(newsRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found")));

        log.info("Saving new News Item: title = {}, newsId = {}, authorId = {}",
                item.getTitle(), item.getNews().getId(), item.getNews().getAuthor().getId());
        return newsItemRepository.save(item);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting News Item with id {}", id);
        newsItemRepository.deleteById(id);
    }

    @Override
    public void deleteAllByNewsId(Long id) {
        List<NewsItem> items = newsItemRepository.findAllByNews_IdOrderByDateOfCreated(id);
        for (NewsItem item : items) {
            newsItemRepository.deleteById(item.getId());
        }
        log.info("Deleting News Items from News with id {}", id);
    }

}
