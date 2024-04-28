package com.example.estudy.domain.lesson.content.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "text_tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TextTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String correctAnswer;

    @OneToOne(mappedBy = "textTask")
    private PracticalContent practicalContent;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

}
