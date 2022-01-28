package com.example.esr.domain.repository;

import com.example.esr.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    /**
     * busca uma lista de restaurantes
     * @return lista de restaurantes
     */
    List<Restaurante> listar();

    /**
     * busca um restaurante por ID
     * @param id Identificador do restaurante
     * @return Restaurante
     */
    Restaurante buscar(Long id);

    /**
     * salva um restaurante no banco de dados
     * @param restaurante
     * @return Restaurante salvo
     */
    Restaurante salvar(Restaurante restaurante);

    /**
     * remove um restaurante do banco de dados
     * @param restaurante
     */
     void remover(Restaurante restaurante);

}
