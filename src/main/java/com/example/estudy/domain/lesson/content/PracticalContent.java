package com.example.estudy.domain.lesson.content;

import com.example.estudy.domain.lesson.content.practical.GapFillingTask;
import com.example.estudy.domain.lesson.content.practical.SortingTask;
import com.example.estudy.domain.lesson.content.practical.Test;
import com.example.estudy.domain.lesson.content.practical.TextTask;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Practical")
public class PracticalContent extends Content {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "practical_content_id")
    private List<Test> tests = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "practical_content_id")
    private List<GapFillingTask> gapFillingTasks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "practical_content_id")
    private List<TextTask> textTasks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "practical_content_id")
    private List<SortingTask> sortingTasks = new ArrayList<>();

}
