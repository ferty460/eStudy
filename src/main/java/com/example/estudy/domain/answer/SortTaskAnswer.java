package com.example.estudy.domain.answer;

import com.example.estudy.domain.lesson.content.practical.SortingTask;
import com.example.estudy.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sorting_task_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortTaskAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "sort_id")
    private SortingTask sortTask;

    @Column(name = "user_answer")
    private String userAnswer;

    private String correctAnswer;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @Column(name = "answer_time")
    private LocalDateTime answerTime;

    @PrePersist
    private void init() {
        answerTime = LocalDateTime.now();
    }

}
