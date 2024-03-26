package com.example.estudy.repository;

import com.example.estudy.domain.module.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findAllByCourseId(Long id);

}
