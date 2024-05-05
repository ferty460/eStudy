package com.example.estudy.domain.answer;

import com.example.estudy.domain.lesson.content.practical.Test;
import com.example.estudy.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Test test;

    @Column(name = "user_answer")
    private Long userAnswer;

    @Column(name = "correct_answer")
    private Long correctAnswer;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @Column(name = "answer_time")
    private LocalDateTime answerTime;

    @PrePersist
    private void init() {
        answerTime = LocalDateTime.now();
    }

}
