package com.example.esr.domain.service;

import com.example.esr.domain.model.Estado;

import java.util.List;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;
import com.example.esr.domain.exception.EntidadeEmUsoException;

public interface EstadoService {

    /**
     * Busca uma lista de estados
     */
    List<Estado> listar();

    /**
     * Adiciona um estado no banco de dados
     * @param estado
     * @return Estado salvo
     */
    Estado salvar(Estado estado);


    /**
     * Deleta uma cozinha do banco de dados
     * @param id Identificador do estado
     * @throws EntidadeNaoEncontradaException
     * @throws EntidadeEmUsoException
     */
    void excluir(Long id);

    /**
     * Busca um estado pelo ID, caso ele não exista lança uma exceção
     * @param id Identificador do Estado
     * @throws EntidadeNaoEncontradaException
     */
    Estado buscarOuFalhar(Long id);

}
