package com.example.estudy.repository;

import com.example.estudy.domain.course.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
