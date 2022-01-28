package com.example.esr.domain.repository;

import com.example.esr.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    /**
     * busca uma lista de estados
     * @return lista de estados
     */
    List<Estado> listar();

    /**
     * busca um estado pelo ID
     * @param id Identificador do estado
     * @return Estado
     */
    Estado buscar(Long id);

    /**
     * salva um estado no banco de dados
     * @param estado
     * @return Estado salvo
     */
    Estado salvar(Estado estado);

    /**
     * remove um estado do banco de dados
     * @param estado
     */
     void remover(Estado estado);

}
