package com.example.estudy.service.dao.course.content.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    Image getById(Long id);

    List<Image> getAllByChapterId(Long id);

    Image create(MultipartFile file, Long chapterId);

    Image update(MultipartFile file, Long imageId);

    void delete(Long id);

}
