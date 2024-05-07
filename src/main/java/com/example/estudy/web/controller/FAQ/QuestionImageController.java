package com.example.estudy.web.controller.FAQ;

import com.example.estudy.domain.FAQ.QuestionImage;
import com.example.estudy.repository.FAQ.QuestionImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class QuestionImageController {

    private final QuestionImageRepository imageRepository;

    @GetMapping("/faq/img/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        QuestionImage image = imageRepository.findById(id).orElse(null);
        assert image != null;
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

}