package com.example.esr.domain.repository;

import com.example.esr.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    /**
     * busca uma lista de cozinhas
     * @return Lista de cozinha
     */
    List<Cozinha> listar();

    /**
     * busca uma cozinha pelo ID
     * @param id Identificador da cozinha
     * @return Cozinha
     */
    Cozinha buscar(Long id);

    /**
     * salva uma cozinha no banco de dados
     * @param cozinha
     * @return Cozinha salva
     */
    Cozinha salvar(Cozinha cozinha);

    /**
     * remove uma cozinha do banco de dados
     * @param id Identificador da cozinha
     */
    void remover(Long id);

    List<Cozinha> consultarPorNome(String nome);

}
