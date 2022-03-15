package com.example.esr.domain.service;

import com.example.esr.domain.model.Grupo;
import com.example.esr.domain.exception.GrupoNaoEcontradoException;
import com.example.esr.domain.exception.EntidadeEmUsoException;
import com.example.esr.domain.exception.NegocioException;

import java.util.List;

public interface GrupoService {

    /**
     * Busca uma lista de grupos
     * @return
     */
    List<Grupo> listar();

    /**
     * Busca um único grupo pelo ID
     * @param id Identificador do grupo
     * @return
     * @throws GrupoNaoEcontradoException
     */
    Grupo buscarOuFalhar(Long id);

    /**
     * Adiciona um grupo no banco de dados
     * @param grupo
     * @return grupo salvo
     */
    Grupo salvar(Grupo grupo);

    /**
     * Deleta um grupo do banco de dados
     * @param id Identificador do grupo
     * @throws GrupoNaoEcontradoException
     * @throws EntidadeEmUsoException
     */
    void excluir(Long id);

    /**
     * Faz a associação de uma permissão a um grupo
     * @param grupoId Identificador da permissão
     * @param permissaoId Identificador do grupo
     * @throws NegocioException
     */
    void associaPermissao(Long grupoId, Long permissaoId);

    /**
     * Faz a desassociação de uma permissão a um grupo
     * @param grupoId Identificador da permissão
     * @param permissaoId Identificador do grupo
     * @throws NegocioException
     */
    void desassociaPermissao(Long grupoId, Long permissaoId);

}
