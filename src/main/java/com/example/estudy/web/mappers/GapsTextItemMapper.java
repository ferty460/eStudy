package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.practical.GapsTextItem;
import com.example.estudy.web.dto.lesson.GapsTextItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GapsTextItemMapper {

    GapsTextItemDto toDto(GapsTextItem item);

    GapsTextItem toEntity(GapsTextItemDto taskDto);

}
