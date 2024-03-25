package com.example.estudy.domain.course;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Availability {

    PUBLIC("Публичный"),
    PRIVATE("Приватный");

    private final String name;

}
