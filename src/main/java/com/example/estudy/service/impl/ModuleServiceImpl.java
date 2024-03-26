package com.example.estudy.service.impl;

import com.example.estudy.domain.module.Module;
import com.example.estudy.repository.CourseRepository;
import com.example.estudy.repository.ModuleRepository;
import com.example.estudy.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    @Override
    public Module getById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found"));
    }

    @Override
    public List<Module> getAllByCourseId(Long id) {
        return moduleRepository.findAllByCourseId(id);
    }

    @Override
    public Module create(Module module, Long courseId) {
        module.setCourse(courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found")));
        return moduleRepository.save(module);
    }

    @Override
    public Module update(Module module, Long moduleId) {
        Module editedModule = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));
        editedModule.setTitle(module.getTitle());
        editedModule.setDescription(module.getDescription());
        return moduleRepository.save(module);
    }

    @Override
    public void delete(Long id) {
        moduleRepository.deleteById(id);
    }

}
