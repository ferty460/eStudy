package com.example.estudy.domain.lesson.content.practical;

import com.example.estudy.domain.answer.TestAnswer;
import com.example.estudy.domain.lesson.content.PracticalContent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "test")
    private List<TestItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "test")
    private List<TestAnswer> answers = new ArrayList<>();

    @OneToOne(mappedBy = "test")
    private PracticalContent practicalContent;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

}
