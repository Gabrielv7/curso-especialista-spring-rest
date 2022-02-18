package com.example.esr.core;

import com.example.esr.api.model.dto.RestauranteDTO;
import com.example.esr.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
                .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);

        return modelMapper;
    }

}
