package com.example.estudy.web.mappers;

import com.example.estudy.domain.FAQ.Question;
import com.example.estudy.web.dto.FAQ.QuestionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto toDto(Question question);

    Question toEntity(QuestionDto questionDto);

}
