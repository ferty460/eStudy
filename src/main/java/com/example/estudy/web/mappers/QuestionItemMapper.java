package com.example.estudy.web.mappers;

import com.example.estudy.domain.FAQ.QuestionItem;
import com.example.estudy.web.dto.FAQ.QuestionItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionItemMapper {

    QuestionItemDto toDto(QuestionItem item);

    QuestionItem toEntity(QuestionItemDto questionDto);

}
