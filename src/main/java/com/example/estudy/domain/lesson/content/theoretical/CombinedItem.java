package com.example.estudy.domain.lesson.content.theoretical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CombinedItem {

    private String content;
    private LocalDateTime dateOfCreated;

}
