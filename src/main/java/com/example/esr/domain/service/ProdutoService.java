package com.example.esr.domain.service;

import com.example.esr.domain.model.Produto;
import com.example.esr.domain.exception.ProdutoNaoEcontradoException;

import java.util.List;

public interface ProdutoService {

    /**
     * Adiciona um produto no banco de dados
     * @param produto
     * @return produto salvo
     */
    Produto salvar(Produto produto);

    /**
     * Busca um único produto baseado no Id de seu restaurante
     * @param restauranteId Identificador do restaurante
     * @param produtoId Identificador do produto
     * @throws ProdutoNaoEcontradoException
     * @return produto
     */
    Produto buscarOuFalhar(Long restauranteId, Long produtoId);

}
