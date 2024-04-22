package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.theoretical.Title;
import com.example.estudy.web.dto.lesson.TitleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TitleMapper {

    TitleDto toDto(Title title);

    Title toEntity(TitleDto titleDto);

}
