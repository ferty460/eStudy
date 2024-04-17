package com.example.estudy.web.mappers;

import com.example.estudy.domain.news.News;
import com.example.estudy.web.dto.news.NewsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsDto toDto(News news);

    News toEntity(NewsDto newsDto);

}
