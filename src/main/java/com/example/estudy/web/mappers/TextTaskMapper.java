package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.practical.TextTask;
import com.example.estudy.web.dto.lesson.TextTaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TextTaskMapper {

    TextTaskDto toDto(TextTask textTask);

    TextTask toEntity(TextTaskDto textTaskDto);

}
