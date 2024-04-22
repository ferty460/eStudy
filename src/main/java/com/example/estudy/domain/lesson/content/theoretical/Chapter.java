package com.example.estudy.domain.lesson.content.theoretical;

import com.example.estudy.domain.lesson.content.TheoreticalContent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chapter_id")
    private List<Title> titles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chapter_id")
    private List<Text> texts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chapter_id")
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theoretical_content_id")
    private TheoreticalContent theoreticalContent;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public List<CombinedItem> getCombinedContent() {
        List<CombinedItem> combinedList = new ArrayList<>();
        for (Title title : titles) {
            combinedList.add(new CombinedItem(title.toString(), title.getDateOfCreated()));
        }
        for (Text text : texts) {
            combinedList.add(new CombinedItem(text.toString(), text.getDateOfCreated()));
        }
        for (Image image : images) {
            combinedList.add(new CombinedItem(image.toString(), image.getDateOfCreated()));
        }
        combinedList.sort(Comparator.comparing(CombinedItem::getDateOfCreated));
        return combinedList;
    }

}
