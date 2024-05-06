package com.example.estudy.domain.answer;

import com.example.estudy.domain.lesson.content.practical.GapsTask;
import com.example.estudy.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "gaps_task_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GapsTaskAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "gaps_id")
    private GapsTask gapsTask;

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
