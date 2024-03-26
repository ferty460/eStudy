package com.example.estudy.service;

import com.example.estudy.domain.module.Module;

import java.util.List;

public interface ModuleService {

    Module getById(Long id);

    List<Module> getAllByCourseId(Long id);

    Module create(Module module, Long courseId);

    Module update(Module module, Long moduleId);

    void delete(Long id);

}
