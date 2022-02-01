package com.example.esr.domain.service.Impl;

import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.repository.RestauranteRepository;
import com.example.esr.domain.service.RestauranteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public List<Restaurante> listar() {

        return restauranteRepository.listar();

    }

    @Override
    public Restaurante buscar(Long id) {

        var restaurante = restauranteRepository.buscar(id);

        if(Objects.nonNull(restaurante)){

            return restaurante;

        }

            return null;
    }
}
