package com.example.estudy.repository.news;

import com.example.estudy.domain.news.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByAuthorId(Long id);

}
