package com.example.estudy.web.mappers;

import com.example.estudy.domain.course.Tag;
import com.example.estudy.web.dto.course.TagDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto toDto(Tag tag);

    Tag toEntity(TagDto tagDto);

}
