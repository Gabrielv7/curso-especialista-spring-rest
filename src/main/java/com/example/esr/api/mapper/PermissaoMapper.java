package com.example.esr.api.mapper;

import com.example.esr.api.model.dto.PermissaoDTO;
import com.example.esr.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoMapper {

    private final ModelMapper mapper;

    public PermissaoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PermissaoDTO toDto(Permissao permissao){

        return mapper.map(permissao, PermissaoDTO.class);

    }

    public List<PermissaoDTO> toCollectionDto(List<Permissao> permissoes){

        return permissoes.stream().map(this::toDto).collect(Collectors.toList());

    }

}
