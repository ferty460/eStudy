package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.practical.GapsValueItem;
import com.example.estudy.web.dto.lesson.GapsValueItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GapsValueItemMapper {

    GapsValueItemDto toDto(GapsValueItem item);

    GapsValueItem toEntity(GapsValueItemDto taskDto);

}
