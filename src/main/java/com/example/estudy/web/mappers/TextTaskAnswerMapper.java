package com.example.estudy.web.mappers;

import com.example.estudy.domain.answer.TextTaskAnswer;
import com.example.estudy.web.dto.answer.TextTaskAnswerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TextTaskAnswerMapper {

    TextTaskAnswerDto toDto(TextTaskAnswer answer);

    TextTaskAnswer toEntity(TextTaskAnswerDto answerDto);

}
