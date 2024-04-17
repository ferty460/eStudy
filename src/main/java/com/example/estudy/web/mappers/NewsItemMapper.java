package com.example.estudy.web.mappers;

import com.example.estudy.domain.news.NewsItem;
import com.example.estudy.web.dto.news.NewsItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsItemMapper {

    NewsItemDto toDto(NewsItem item);

    NewsItem toEntity(NewsItemDto itemDto);

}
