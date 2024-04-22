package com.example.estudy.web.mappers;

import com.example.estudy.domain.lesson.content.theoretical.Chapter;
import com.example.estudy.web.dto.lesson.ChapterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    ChapterDto toDto(Chapter chapter);

    Chapter toEntity(ChapterDto chapterDto);

}
