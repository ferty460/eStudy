package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.practical.GapsTask;
import com.example.estudy.web.dto.lesson.GapsTaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GapsTaskMapper {

    GapsTaskDto toDto(GapsTask task);

    GapsTask toEntity(GapsTaskDto taskDto);

}
