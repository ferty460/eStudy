package com.example.estudy.web.dto.course;

import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TagDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 256, message = "Name must be smaller than 256 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String name;

}
