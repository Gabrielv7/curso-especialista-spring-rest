package com.example.esr.domain.service;

import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;

import java.util.List;

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
     * Deleta uma cozinha do banco de dados
     * @param id Identificador da cozinha
     * @throws EntidadeEmUsoException
     * @throws EntidadeNaoEncontradaException
     */
     void excluir(Long id);

    /**
     * Busca uma cozinha pelo ID, caso ela não exista lança uma exceção
     * @param id Identificador da cozinha
     * @throws EntidadeNaoEncontradaException
     */
     Cozinha buscarOuFalhar(Long id);


}
