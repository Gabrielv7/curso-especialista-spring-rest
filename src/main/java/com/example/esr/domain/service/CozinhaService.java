package com.example.esr.domain.service;

import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;

import java.util.List;
import java.util.Optional;

public interface CozinhaService {

    /**
     * Adiciona um cozinha no banco de dados
     * @param cozinha
     * @return Cozinha salva
     */
    Cozinha salvar(Cozinha cozinha);

    /**
     * Busca uma lista de cozinhas
     */
    List<Cozinha> listar();

    /**
     * Busca uma cozinha
     * @param id Identificador da cozinha
     * @return Cozinha
     */
    Optional<Cozinha> buscar(Long id);

    /**
     * Deleta uma cozinha do banco de dados
     * @param id Identificador da cozinha
     * @throws EntidadeEmUsoException
     * @throws EntidadeNaoEncontradaException
     */
     void excluir(Long id);

    /**
     * Busca uma cozinha, caso ela não exista lança uma exceção
     * @param id Identificador da cozinha
     * @throws EntidadeNaoEncontradaException
     */
     Cozinha buscarOuFalhar(Long id);


}
