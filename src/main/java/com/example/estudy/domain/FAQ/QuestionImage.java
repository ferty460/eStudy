package com.example.estudy.domain.FAQ;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question_images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "originalFileName")
    private String originalFileName;

    @Column(name = "size")
    private Long size;

    @Column(name = "contentType")
    private String contentType;

    @Column(name = "isPreviewImage")
    private boolean isPreviewImage;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] bytes;

    @OneToOne(mappedBy = "image")
    private Question question;

}
