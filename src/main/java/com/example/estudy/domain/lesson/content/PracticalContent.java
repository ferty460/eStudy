package com.example.estudy.domain.lesson.content;

import com.example.estudy.domain.lesson.content.practical.GapsTask;
import com.example.estudy.domain.lesson.content.practical.SortingTask;
import com.example.estudy.domain.lesson.content.practical.Test;
import com.example.estudy.domain.lesson.content.practical.TextTask;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Practical")
public class PracticalContent extends Content {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gaps_task_id")
    private GapsTask gapsTask;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_task_id")
    private TextTask textTask;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sorting_task_id")
    private SortingTask sortingTask;

    private String practicalType;

    public void setTest(Test test) {
        this.test = test;
        this.gapsTask = null;
        this.textTask = null;
        this.sortingTask = null;
    }

    public void setGapsTask(GapsTask gapsTask) {
        this.test = null;
        this.gapsTask = gapsTask;
        this.textTask = null;
        this.sortingTask = null;
    }

    public void setTextTask(TextTask textTask) {
        this.test = null;
        this.gapsTask = null;
        this.textTask = textTask;
        this.sortingTask = null;
    }

    public void setSortingTask(SortingTask sortingTask) {
        this.test = null;
        this.gapsTask = null;
        this.textTask = null;
        this.sortingTask = sortingTask;
    }

}
