package com.example.estudy.web.dto.user;

import com.example.estudy.web.dto.validation.OnCreate;
import com.example.estudy.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 33, message = "Name must be smaller than 33 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "Surname must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 33, message = "Surname must be smaller than 33 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String surname;

    @Length(max = 33, message = "Patronymic must be smaller than 33 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String patronymic;

    @NotNull(message = "Username must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 33, message = "Username must be smaller than 33 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    private String gender;

    private Integer age;

    @NotNull(message = "Email must be not null", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    /*@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null", groups = {OnCreate.class, OnUpdate.class})
    private String password;*/

}
