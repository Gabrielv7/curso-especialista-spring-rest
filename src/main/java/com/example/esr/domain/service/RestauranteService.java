package com.example.esr.domain.service;

import com.example.esr.domain.model.Restaurante;
import com.example.esr.domain.exception.EntidadeNaoEncontradaException;

import java.util.List;
import java.util.Map;

public interface RestauranteService {

    /**
     * Busca uma lista de restaurantes
     */
    List<Restaurante> listar();

    /**
     * Busca um restaurante
     * @param id Identificador do restaurante
     * @return Restaurante
     */
    Restaurante buscar(Long id);

    /**
     * Adiciona um restaurante no banco de dados
     * @param restaurante
     * @throws EntidadeNaoEncontradaException
     * @return Restaurante Salvo
     */
    Restaurante salvar(Restaurante restaurante);

    /**
     * Faz a atualização parcial de um restaurante
     * @param camposOrigem Campos que veio no parametro para a atualização
     * @param restauranteDestino Restaurante que vai ser atualizado
     */
    void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino);

}
