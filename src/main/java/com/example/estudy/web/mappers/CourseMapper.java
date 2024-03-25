package com.example.estudy.web.mappers;

import com.example.estudy.domain.course.Course;
import com.example.estudy.web.dto.course.CourseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto toDto(Course course);

    List<CourseDto> toDto(List<Course> courses);

    Course toEntity(CourseDto courseDto);

}
