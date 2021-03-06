package com.example.esr.api.mapper;

import com.example.esr.api.model.dto.RestauranteDTO;
import com.example.esr.api.model.input.restaurente.RestauranteInput;
import com.example.esr.domain.model.Cidade;
import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RestauranteMapper {

    final ModelMapper modelMapper;

    public RestauranteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RestauranteDTO toDto(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<RestauranteDTO> toCollectionDto(List<Restaurante> restaurantes){

        return restaurantes.stream().map(this::toDto).collect(Collectors.toList());

    }

    public Restaurante toEntity(RestauranteInput restauranteInput){

        return modelMapper.map(restauranteInput, Restaurante.class);

    }

    public void copyToEntity(RestauranteInput restauranteInput, Restaurante restaurante){

        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com/example/esr/domain/model/Cozinha.java was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());

        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com/example/esr/domain/model/Cozinha.java was altered from 1 to 2
        if(Objects.nonNull(restaurante.getEndereco())) {
            restaurante.getEndereco().setCidade(new Cidade());
        }

        modelMapper.map(restauranteInput, restaurante);

    }

}
