package com.example.estudy.domain.lesson.content.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sorting_tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortingTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    // sorting task fields

    @OneToOne(mappedBy = "sortingTask")
    private PracticalContent practicalContent;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

}
