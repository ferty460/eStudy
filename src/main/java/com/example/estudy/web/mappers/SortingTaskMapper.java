package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.practical.SortingTask;
import com.example.estudy.web.dto.lesson.SortingTaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SortingTaskMapper {

    SortingTaskDto toDto(SortingTask sortingTask);

    SortingTask toEntity(SortingTaskDto sortingTaskDto);

}
