package com.example.estudy.service;

import com.example.estudy.domain.news.News;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NewsService {

    News getById(Long id);

    List<News> getAll();

    List<News> getAllByUserId(Long id);

    News update(News news, Long newsId, MultipartFile file) throws IOException;

    News create(News news, Long userId, MultipartFile file) throws IOException;

    void delete(Long id);

}
