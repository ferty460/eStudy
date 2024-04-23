package com.example.estudy.domain.lesson.content.theoretical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "<div class=\"lesson-actions\"><div></div>" +
                "<img src=\"/theoretical/img/" + id + "\" alt=\"lesson image\" class=\"lesson-image\">" +
                "<div class=\"module-actions\" style=\"min-width: 50px; align-items: start;\">" +
                "<a href=\"\" data-hystmodal='#edit_image" + id + "'><img src=\"/images/settings.svg\" alt=\"edit\" title=\"Редактировать\"></a>\n" +
                "<a href=\"\" data-hystmodal='#delete_image" + id + "'><img src=\"/images/delete.svg\" alt=\"delete\"></a>" +
                "</div>" +
                "</div>";
    }
}
