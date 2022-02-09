package com.example.esr.domain.service;

import com.example.esr.domain.model.Cidade;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.exception.EntidadeEmUsoException;

import java.util.List;

public interface CidadeService {

    /**
     * Busca uma lista de cidades
     */
    List<Cidade> listar();

    /**
     * Adiciona uma cidade no banco de dados
     * @param cidade
     * @throws EntidadeNaoEncontradaException
     * @return Cidade salva
     */
    Cidade salvar (Cidade cidade);

    /**
     * Deleta uma cozinha do banco de dados
     * @param id Identificador da cozinha
     * @throws EntidadeNaoEncontradaException
     * @throws EntidadeEmUsoException
     */
    void excluir(Long id);

    /**
     * Busca uma Cidade pelo ID, caso ela não exista lança uma exceção
     * @param id Identificador do Estado
     * @throws EntidadeNaoEncontradaException
     */
    Cidade buscarOuFalhar(Long id);


}
