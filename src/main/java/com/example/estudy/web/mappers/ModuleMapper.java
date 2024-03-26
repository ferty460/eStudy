package com.example.estudy.web.mappers;

import com.example.estudy.domain.module.Module;
import com.example.estudy.web.dto.module.ModuleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

    ModuleDto toDto(Module module);

    List<ModuleDto> toDto(List<Module> modules);

    Module toEntity(ModuleDto moduleDto);

}
