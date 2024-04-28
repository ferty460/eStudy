package com.example.estudy.domain.lesson.content.theoretical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "titles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    private LocalDateTime dateOfCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "<div class=\"lesson-actions\">" +
                "<h4 class=\"lesson-title\">" + value + "</h4>" +
                "<div class=\"module-actions\" style=\"min-width: 50px; align-items: start;\">" +
                "<a href=\"\" data-hystmodal='#edit_title" + id + "'><img src=\"/images/settings.svg\" alt=\"edit\" title=\"Редактировать\"></a>\n" +
                "<a href=\"\" data-hystmodal='#delete_title" + id + "'><img src=\"/images/delete.svg\" alt=\"delete\"></a>" +
                "</div>" +
                "</div>";
    }
}
