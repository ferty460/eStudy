package com.example.estudy.web.dto.lesson;

import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SortingTaskElementDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Content must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 256, message = "Content must be smaller than 256 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String content;

}
