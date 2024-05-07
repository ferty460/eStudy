package com.example.estudy.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_access_links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseAccessLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(unique = true)
    private String accessCode;

    private boolean isActive;

    private LocalDateTime dateOfCreated;

    public CourseAccessLink(Course course, String accessCode) {
        this.course = course;
        this.accessCode = accessCode;
        this.isActive = true;
        this.dateOfCreated = LocalDateTime.now();
    }

}
