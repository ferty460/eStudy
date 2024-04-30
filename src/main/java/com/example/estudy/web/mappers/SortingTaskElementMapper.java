package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.practical.SortingTaskElement;
import com.example.estudy.web.dto.lesson.SortingTaskElementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SortingTaskElementMapper {

    SortingTaskElementDto toDto(SortingTaskElement element);

    SortingTaskElement toEntity(SortingTaskElementDto elementDto);

}
