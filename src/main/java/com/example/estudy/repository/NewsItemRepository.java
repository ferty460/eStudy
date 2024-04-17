package com.example.estudy.repository;

import com.example.estudy.domain.news.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsItemRepository extends JpaRepository<NewsItem, Long> {

    List<NewsItem> findAllByNews_IdOrderByDateOfCreated(Long id);

    void deleteAllByNews_Id(Long id);

}
