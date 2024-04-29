package com.example.estudy.service.details;

import com.example.estudy.domain.user.CustomOAuth2User;
import com.example.estudy.domain.user.Role;
import com.example.estudy.domain.user.User;
import com.example.estudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        return processOAuth2User(user);
    }

    private OAuth2User processOAuth2User(OAuth2User oauth2User) {
        CustomOAuth2User customOauth2User = new CustomOAuth2User(oauth2User);

        User user = userRepository.findByUsername(customOauth2User.getUsername());
        if (user == null) {
            user = new User();
            user.setName(customOauth2User.getName());
            user.setSurname(customOauth2User.getSurname());
            user.setUsername(customOauth2User.getUsername());
            user.setEmail(customOauth2User.getEmail());
            user.getRoles().add(Role.ROLE_USER);

            String password = UUID.randomUUID().toString();
            user.setPassword(password);

            userRepository.save(user);
        }

        return customOauth2User;
    }
}
