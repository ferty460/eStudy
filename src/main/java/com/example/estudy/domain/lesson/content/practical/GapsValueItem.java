package com.example.estudy.domain.lesson.content.practical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "gaps_values")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GapsValueItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private GapsTask task;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public String getBlockToUser() {
        return "<span><input class=\"gaps-bar1 gaps-input1\" name=\"gaps\" id=\"gap" + id + "\" type=\"text\"></span>";
    }

    @Override
    public String toString() {
        return "<div class=\"text-spaces-item\">" +
                "<div class=\"news-title__block\">" +
                "<label class=\"label\">Пропуск:</label>" +
                "<p class=\"label\" style=\"color: #c5c5c5;\">" + value + "</p>" +
                "</div></div>" +
                "<button style=\"width: 20px;\" data-hystmodal=\"#delete_value" + id + "\" type=\"button\">" +
                "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 20 20\">" +
                "<line x1=\"0\" y1=\"0\" x2=\"20\" y2=\"20\" stroke=\"#6C7293\" stroke-width=\"3\" />" +
                "<line x1=\"20\" y1=\"0\" x2=\"0\" y2=\"20\" stroke=\"#6C7293\" stroke-width=\"3\" />" +
                "</svg></button>";
    }
}
