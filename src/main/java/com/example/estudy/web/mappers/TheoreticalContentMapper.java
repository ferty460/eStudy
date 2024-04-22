package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.TheoreticalContent;
import com.example.estudy.web.dto.lesson.TheoreticalContentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheoreticalContentMapper {

    TheoreticalContentDto toDto(TheoreticalContent content);

    TheoreticalContent toEntity(TheoreticalContentDto contentDto);

}
