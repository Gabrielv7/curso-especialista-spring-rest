package com.example.esr.domain.service;

import com.example.esr.domain.model.Cozinha;
import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;

import java.util.List;

public interface CozinhaService {

    /**
     * Adiciona um cozinha no banco de dados
     * @param cozinha
     * @return Cozinha Salva
     */
    Cozinha salvar(Cozinha cozinha);

    /**
     * Retorna uma lista de cozinhas
     */
    List<Cozinha> listar();

    /**
     * Retorna uma cozinha
     * @param id Identificador da cozinha
     * @return Cozinha
     */
    Cozinha buscar(Long id);

    /**
     * Deleta uma cozinha do banco de dados
     * @param id Identificador da cozinha
     * @throws EntidadeEmUsoException
     * @throws EntidadeNaoEncontradaException
     */
     void excluir(Long id);


}
