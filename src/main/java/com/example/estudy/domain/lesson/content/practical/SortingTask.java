package com.example.estudy.domain.lesson.content.practical;

import com.example.estudy.domain.answer.SortTaskAnswer;
import com.example.estudy.domain.lesson.content.PracticalContent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @OneToMany(mappedBy = "task")
    @OrderBy("position")
    private List<SortingTaskElement> elements = new ArrayList<>();

    @OneToMany(mappedBy = "sortTask")
    private List<SortTaskAnswer> taskAnswers = new ArrayList<>();

    @OneToOne(mappedBy = "sortingTask")
    private PracticalContent practicalContent;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public List<SortingTaskElement> getShuffledElements() {
        List<SortingTaskElement> shuffledElements = new ArrayList<>(elements);
        Collections.shuffle(shuffledElements);
        return shuffledElements;
    }

}
