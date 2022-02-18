package com.example.esr.api.mapper;

import com.example.esr.api.model.dto.CozinhaDTO;
import com.example.esr.api.model.input.cozinha.CozinhaInput;
import com.example.esr.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaMapper {

    final ModelMapper modelMapper;

    public CozinhaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CozinhaDTO toDto(Cozinha cozinha){

        return modelMapper.map(cozinha, CozinhaDTO.class);

    }

    public List<CozinhaDTO> toCollectionDto(List<Cozinha> cozinhas){

        return cozinhas.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Cozinha toEntity(CozinhaInput cozinhaInput){

        return modelMapper.map(cozinhaInput, Cozinha.class);

    }

    public void copyToEntity(CozinhaInput cozinhaInput, Cozinha cozinha){

        modelMapper.map(cozinhaInput, cozinha);

    }

}
