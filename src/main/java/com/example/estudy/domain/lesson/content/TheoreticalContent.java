package com.example.estudy.domain.lesson.content;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;
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
@DiscriminatorValue("Theoretical")
public class TheoreticalContent extends Content {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "theoretical_content_id")
    private List<Chapter> chapters = new ArrayList<>();

}
