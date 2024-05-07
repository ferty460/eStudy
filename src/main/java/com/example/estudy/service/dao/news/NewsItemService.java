package com.example.estudy.service.dao.news;

import com.example.estudy.domain.news.NewsItem;

import java.util.List;

public interface NewsItemService {

    NewsItem getById(Long id);

    List<NewsItem> getAll();

    List<NewsItem> getAllByNewsId(Long id);

    NewsItem update(NewsItem item, Long itemId);

    NewsItem create(NewsItem item, Long newsId);

    void delete(Long id);

    void deleteAllByNewsId(Long id);

}
