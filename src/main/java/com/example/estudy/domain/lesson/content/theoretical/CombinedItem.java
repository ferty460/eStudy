package com.example.estudy.domain.lesson.content.theoretical;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CombinedItem {

    private String content;
    private LocalDateTime dateOfCreated;

    public CombinedItem(String content, LocalDateTime dateOfCreated) {
        this.content = content;
        this.dateOfCreated = dateOfCreated;
    }

}
