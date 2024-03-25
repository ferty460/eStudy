package com.example.estudy.web.mappers;

import com.example.estudy.domain.user.User;
import com.example.estudy.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}
