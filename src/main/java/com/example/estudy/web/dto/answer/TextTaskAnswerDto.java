package com.example.estudy.web.dto.answer;

import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TextTaskAnswerDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "User answer must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 33, message = "User answer must be smaller than 33 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String userAnswer;

}
