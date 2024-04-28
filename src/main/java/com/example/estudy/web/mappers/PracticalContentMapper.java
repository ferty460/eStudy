package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.PracticalContent;
import com.example.estudy.web.dto.lesson.PracticalContentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PracticalContentMapper {

    PracticalContentDto toDto(PracticalContent content);

    PracticalContent toEntity(PracticalContentDto contentDto);

}
