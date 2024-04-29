package com.example.estudy.web.dto.lesson;

import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TestItemDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Value must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 33, message = "Value must be smaller than 33 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String value;

}
