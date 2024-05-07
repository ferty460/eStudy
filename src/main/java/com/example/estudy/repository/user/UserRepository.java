package com.example.estudy.repository.user;

import com.example.estudy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

}
