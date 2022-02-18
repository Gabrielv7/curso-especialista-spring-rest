package com.example.esr.api.mapper;

import com.example.esr.api.model.dto.RestauranteDTO;
import com.example.esr.api.model.input.RestauranteInput;
import com.example.esr.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteMapper {

    final ModelMapper modelMapper;

    public RestauranteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RestauranteDTO toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<RestauranteDTO> toCollectionDTO (List<Restaurante> restaurantes){

        return restaurantes.stream().map(this::toModel).collect(Collectors.toList());

    }

    public Restaurante toEntity(RestauranteInput restauranteInput){

        return modelMapper.map(restauranteInput, Restaurante.class);

    }

}