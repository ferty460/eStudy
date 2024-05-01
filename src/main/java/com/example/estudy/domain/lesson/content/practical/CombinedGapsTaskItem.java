package com.example.estudy.domain.lesson.content.practical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CombinedGapsTaskItem {

    private String content;
    private LocalDateTime dateOfCreated;

}
