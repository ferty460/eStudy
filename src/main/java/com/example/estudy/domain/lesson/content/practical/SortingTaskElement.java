package com.example.estudy.domain.lesson.content.practical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sorting_task_elements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortingTaskElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private SortingTask task;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int position;

}
