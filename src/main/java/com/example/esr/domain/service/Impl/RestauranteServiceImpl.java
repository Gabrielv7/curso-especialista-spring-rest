package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.repository.CozinhaRepository;
import com.example.esr.domain.repository.RestauranteRepository;
import com.example.esr.domain.service.RestauranteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
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

    @Override
    public Restaurante salvar(Restaurante restaurante) {

        // Pega o ID da cozinha
        long cozinhaId  = restaurante.getCozinha().getId();

        // busca a cozinha pelo Id
        var cozinha = cozinhaRepository.buscar(cozinhaId);

        // Se a cozinha não existir lança a exeção EntidadeNaoEncontradaException
        if(Objects.isNull(cozinha)){

            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com o código %d.", cozinhaId)
            );

        }

        // Se a cozinha existir ela é adicionada ao restaurante
        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }
}
