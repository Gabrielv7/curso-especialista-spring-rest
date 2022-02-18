package com.example.esr.api.mapper;

import com.example.esr.api.model.dto.CidadeDTO;
import com.example.esr.api.model.input.cidade.CidadeInput;
import com.example.esr.domain.model.Cidade;
import com.example.esr.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeMapper {

    private final ModelMapper modelMapper;

    public CidadeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public CidadeDTO toDto(Cidade cidade){

        return modelMapper.map(cidade, CidadeDTO.class);

    }

    public List<CidadeDTO> toCollectionDto(List<Cidade> cidades){

        return cidades.stream().map(this::toDto).collect(Collectors.toList());

    }

    public Cidade toEntity(CidadeInput cidadeInput){

        return modelMapper.map(cidadeInput, Cidade.class);

    }

    public void copyToEntity(CidadeInput cidadeInput, Cidade cidade){

        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        cidade.setEstado(new Estado());

        modelMapper.map(cidadeInput, cidade);

    }

}
