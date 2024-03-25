package com.example.estudy.service;

import com.example.estudy.domain.course.Tag;

import java.util.List;

public interface TagService {

    Tag getById(Long id);

    Tag update(Tag tag, Long id);

    Tag create(Tag tag);

    void delete(Long id);

    List<Tag> getAll();

}
