package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.practical.Test;
import com.example.estudy.web.dto.lesson.TestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper {

    TestDto toDto(Test test);

    Test toEntity(TestDto testDto);

}
