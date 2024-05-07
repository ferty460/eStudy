package com.example.estudy.service.impl.course.content.theoretical;

import com.example.estudy.domain.lesson.content.theoretical.Image;
import com.example.estudy.repository.course.content.theoretical.ChapterRepository;
import com.example.estudy.repository.course.content.theoretical.ImageRepository;
import com.example.estudy.service.dao.course.content.theoretical.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ChapterRepository chapterRepository;

    @Override
    public Image getById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
    }

    @Override
    public List<Image> getAllByChapterId(Long id) {
        return imageRepository.findAllByChapter_Id(id);
    }

    @Override
    public Image create(MultipartFile file, Long chapterId) {
        try {
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setOriginalFileName(file.getOriginalFilename());
            image.setSize(file.getSize());
            image.setContentType(file.getContentType());
            image.setBytes(file.getBytes());
            image.setChapter(chapterRepository.findById(chapterId)
                    .orElseThrow(() -> new RuntimeException("Chapter not found")));

            log.info("Saving new Image: chapterId = {}, name = {}", chapterId, image.getName());
            return imageRepository.save(image);
        } catch (IOException e) {
            log.error("Failed to save the Image: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Image update(MultipartFile file, Long imageId) {
        try {
            Image editedImage = imageRepository.findById(imageId)
                    .orElseThrow(() -> new RuntimeException("Image not found"));
            editedImage.setName(file.getOriginalFilename());
            editedImage.setOriginalFileName(file.getOriginalFilename());
            editedImage.setSize(file.getSize());
            editedImage.setContentType(file.getContentType());
            editedImage.setBytes(file.getBytes());

            log.info("Editing Image with id {}", imageId);
            return imageRepository.save(editedImage);
        } catch (IOException e) {
            log.error("Failed to update the Image: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting image with id {}", id);
        imageRepository.deleteById(id);
    }

}
