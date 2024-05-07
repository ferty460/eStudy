package com.example.estudy.web.dto.news;

import com.example.estudy.domain.user.User;
import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class NewsDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @Length(max = 256, message = "Title must be smaller than 256 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    private String description;

    private User author;

}
