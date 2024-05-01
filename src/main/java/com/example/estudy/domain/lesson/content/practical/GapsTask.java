package com.example.estudy.domain.lesson.content.practical;

import com.example.estudy.domain.lesson.content.PracticalContent;
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
@Table(name = "gaps_tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GapsTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<GapsTextItem> texts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<GapsValueItem> values = new ArrayList<>();

    @OneToOne(mappedBy = "gapsTask")
    private PracticalContent practicalContent;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public List<CombinedGapsTaskItem> getCombinedContent() {
        List<CombinedGapsTaskItem> combinedList = new ArrayList<>();
        for (GapsTextItem item : texts) {
            combinedList.add(new CombinedGapsTaskItem(item.toString(), item.getDateOfCreated()));
        }
        for (GapsValueItem value : values) {
            combinedList.add(new CombinedGapsTaskItem(value.toString(), value.getDateOfCreated()));
        }
        combinedList.sort(Comparator.comparing(CombinedGapsTaskItem::getDateOfCreated));
        return combinedList;
    }

    public List<CombinedGapsTaskItem> getBlocksToUser() {
        List<CombinedGapsTaskItem> combinedList = new ArrayList<>();
        for (GapsTextItem item : texts) {
            combinedList.add(new CombinedGapsTaskItem(item.getBlockToUser(), item.getDateOfCreated()));
        }
        for (GapsValueItem value : values) {
            combinedList.add(new CombinedGapsTaskItem(value.getBlockToUser(), value.getDateOfCreated()));
        }
        combinedList.sort(Comparator.comparing(CombinedGapsTaskItem::getDateOfCreated));
        return combinedList;
    }

}
