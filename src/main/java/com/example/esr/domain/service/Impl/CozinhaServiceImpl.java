package com.example.esr.domain.service.Impl;

import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.repository.CozinhaRepository;
import com.example.esr.domain.service.CozinhaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CozinhaServiceImpl implements CozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public CozinhaServiceImpl(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @Override
    public Cozinha salvar(Cozinha cozinha) {

        return cozinhaRepository.salvar(cozinha);

    }

    @Override
    public List<Cozinha> listar() {

        return cozinhaRepository.listar();

    }

    @Override
    public Cozinha buscar(Long id) {

        var cozinha = cozinhaRepository.buscar(id);

        if(Objects.nonNull(cozinha)){

            return cozinha;

        }

        return null;
    }

    @Override
    public void excluir(Long id) {

        try {

            cozinhaRepository.remover(id);

        }catch (EmptyResultDataAccessException ex){

            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com o código %d.", id)
            );

        } catch (DataIntegrityViolationException ex){

            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não poder ser removida, pois está em uso", id)
            );

        }

    }
}
