package com.example.esr.core;

import com.example.esr.api.model.dto.EnderecoDTO;
import com.example.esr.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        var modelMapper = new ModelMapper();

     //   modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
     //        .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);

       var enderecoToEnderecoModelTypeMap =  modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);

       enderecoToEnderecoModelTypeMap.<String>addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
               (enderecoDtoDest, value) -> enderecoDtoDest.getCidade().setEstado(value));

        return modelMapper;
    }

}
