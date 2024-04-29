package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.practical.TestItem;
import com.example.estudy.web.dto.lesson.TestItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestItemMapper {

    TestItemDto toDto(TestItem testItem);

    TestItem toEntity(TestItemDto testItemDto);

}
