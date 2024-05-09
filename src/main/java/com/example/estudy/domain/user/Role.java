package com.example.estudy.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ROLE_USER("Пользователь"),
    ROLE_MODER("Модератор"),
    ROLE_ADMIN("Администратор");

    private final String name;

    @Override
    public String getAuthority() {
        return name();
    }

}
