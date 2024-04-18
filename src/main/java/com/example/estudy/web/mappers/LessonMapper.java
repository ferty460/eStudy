package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.Lesson;
import com.example.estudy.web.dto.lesson.LessonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonDto toDto(Lesson lesson);

    Lesson toEntity(LessonDto lessonDto);

}
