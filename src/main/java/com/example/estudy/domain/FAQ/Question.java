package com.example.estudy.domain.FAQ;

import com.example.estudy.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    /* --------- IMAGE --------- */

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private QuestionImage image;

    public void setImage(QuestionImage image) {
        image.setQuestion(this);
        this.image = image;
    }

}
