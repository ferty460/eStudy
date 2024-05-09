package com.example.estudy.service.impl.course;

import com.example.estudy.domain.course.Tag;
import com.example.estudy.repository.course.TagRepository;
import com.example.estudy.service.dao.course.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag getById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag with id " + id + " not found"));
    }

    @Override
    public Tag update(Tag tag, Long id) {
        Tag editedTag = tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
        editedTag.setName(tag.getName());
        return tagRepository.save(editedTag);
    }

    @Override
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
