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
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String value;

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
        return "<div class=\"lesson-actions\">" +
                "<p class=\"lesson-text\">" + value + "</p>" +
                "<div class=\"module-actions\" style=\"min-width: 50px; align-items: start;\">" +
                "<a href=\"\" data-hystmodal='#edit_text" + id + "'><img src=\"/images/settings.svg\" alt=\"edit\" title=\"Редактировать\"></a>\n" +
                "<a href=\"\" data-hystmodal='#delete_text" + id + "'><img src=\"/images/delete.svg\" alt=\"delete\"></a>" +
                "</div>" +
                "</div>";
    }
}