package com.example.esr.api.mapper;

import com.example.esr.api.model.dto.CozinhaDTO;
import com.example.esr.api.model.dto.RestauranteDTO;
import com.example.esr.api.model.input.RestauranteInput;
import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteMapper {

    public RestauranteDTO toModel(Restaurante restaurante) {

        var restauranteDto = new RestauranteDTO();
        var cozinhaDto = new CozinhaDTO();

        cozinhaDto.setId(restaurante.getCozinha().getId());
        cozinhaDto.setNome(restaurante.getCozinha().getNome());

        restauranteDto.setId(restaurante.getId());
        restauranteDto.setNome(restaurante.getNome());
        restauranteDto.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteDto.setCozinha(cozinhaDto);
        return restauranteDto;
    }

    public List<RestauranteDTO> toCollectionDTO (List<Restaurante> restaurantes){

        return restaurantes.stream().map(this::toModel).collect(Collectors.toList());

    }

    public Restaurante toDomainObject (RestauranteInput restauranteInput){

        var restaurante = new Restaurante();

        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        var cozinha = new Cozinha();

        cozinha.setId(restauranteInput.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;


    }

}
