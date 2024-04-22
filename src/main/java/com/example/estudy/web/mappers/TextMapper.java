package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.theoretical.Text;
import com.example.estudy.web.dto.lesson.TextDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TextMapper {

    TextDto toDto(Text text);

    Text toEntity(TextDto textDto);

}
