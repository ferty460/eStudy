package com.example.estudy.web.dto.lesson;

import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GapsTextItemDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Text must be not null", groups = {OnCreate.class, OnUpdate.class})
    private String text;

}
