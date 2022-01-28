package com.example.esr.domain.repository;

import com.example.esr.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    /**
     * busca uma lista de cidades
     * @return lista de cidades
     */
    List<Cidade> listar();

    /**
     * busca uma cidade pelo ID
     * @param id Identificador da cidade
     * @return Cidade
     */
    Cidade buscar(Long id);

    /**
     * salva uma cidade no banco de dados
     * @param cidade
     * @return Cidade salva
     */
    Cidade salvar(Cidade cidade);

    /**
     * remove uma cidade do banco de dados
     * @param cidade
     */
     void remover(Cidade cidade);

}
