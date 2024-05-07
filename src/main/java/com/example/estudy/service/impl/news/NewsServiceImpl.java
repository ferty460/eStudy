package com.example.estudy.service.impl.news;

import com.example.estudy.domain.news.News;
import com.example.estudy.domain.news.NewsImage;
import com.example.estudy.repository.news.NewsImageRepository;
import com.example.estudy.repository.news.NewsRepository;
import com.example.estudy.repository.user.UserRepository;
import com.example.estudy.service.dao.news.NewsService;
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
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final NewsImageRepository imageRepository;

    @Override
    @Transactional(readOnly = true)
    public News getById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<News> getAllByUserId(Long id) {
        return newsRepository.findAllByAuthorId(id);
    }

    @Override
    @Transactional
    public News update(News news, Long newsId, MultipartFile file) throws IOException {
        News editedNews = getById(newsId);

        imageRepository.deleteById(editedNews.getImage().getId());
        log.info("Image with id {} was deleted", editedNews.getImage().getId());

        editedNews.setTitle(news.getTitle());
        editedNews.setDescription(news.getDescription());
        if (file.getSize() != 0) {
            NewsImage image = toImageEntity(file);
            editedNews.setImage(image);
        }

        editedNews = newsRepository.save(editedNews);

        log.info("Editing News with id {}", news.getId());
        return editedNews;
    }

    @Override
    @Transactional
    public News create(News news, Long userId, MultipartFile file) throws IOException {
        news.setAuthor(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id " + userId + " not found")));
        if (file.getSize() != 0) {
            NewsImage image = toImageEntity(file);
            news.setImage(image);
        }

        log.info("Saving new News: title = {}, authorId = {}", news.getTitle(), news.getAuthor().getId());
        return newsRepository.save(news);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        News news = getById(id);

        imageRepository.deleteById(news.getImage().getId());
        log.info("Image with id {} was deleted", news.getImage().getId());

        newsRepository.deleteById(id);
        log.info("Deleting News with id {}", id);
    }

    /* --------- IMAGE --------- */

    private NewsImage toImageEntity(MultipartFile file) throws IOException {
        NewsImage image = new NewsImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());

        return image;
    }

}
