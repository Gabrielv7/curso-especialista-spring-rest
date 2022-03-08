package com.example.esr.api.mapper;

import com.example.esr.api.model.dto.GrupoDTO;
import com.example.esr.api.model.input.grupo.GrupoInput;
import com.example.esr.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoMapper {

    private final ModelMapper modelMapper;

    public GrupoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public GrupoDTO toDto(Grupo grupo){

        return modelMapper.map(grupo, GrupoDTO.class);

    }

    public List<GrupoDTO> toCollectionDto(List<Grupo> grupos){

        return grupos.stream().map(this::toDto).collect(Collectors.toList());

    }

    public Grupo toEntity(GrupoInput grupoInput){

        return modelMapper.map(grupoInput, Grupo.class);

    }

    public void copyToEntity(GrupoInput grupoInput, Grupo grupo){

        modelMapper.map(grupoInput, grupo);

    }

}
