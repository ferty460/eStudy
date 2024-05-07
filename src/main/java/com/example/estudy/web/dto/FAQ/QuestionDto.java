package com.example.estudy.web.dto.FAQ;

import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class QuestionDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @Length(max = 256, message = "Title must be smaller than 256 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    private String description;

}
