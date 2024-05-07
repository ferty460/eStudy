package com.example.estudy.web.dto.course;

import com.example.estudy.domain.course.Availability;
import com.example.estudy.domain.course.Tag;
import com.example.estudy.domain.user.User;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CourseDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Title must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 256, message = "Title must be smaller than 256 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    private String description;

    private User author;

    @NotNull(message = "Tag must be not null", groups = {OnCreate.class})
    private Tag tag;

    @NotNull(message = "Availability must be not null", groups = {OnCreate.class})
    private Availability availability;

    private Float rating;

}
