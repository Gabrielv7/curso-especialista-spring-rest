package com.example.esr.domain.repository;
import com.example.esr.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    /**
     * busca uma lista de permissões
     * @return lista de permissões
     */
    List<Permissao> listar();

    /**
     * busca uma permissao pelo ID
     * @param id Identificador da permissão
     * @return Permissao
     */
    Permissao buscar(Long id);

    /**
     * salva uma permissão no banco de dados
     * @param permissao
     * @return Permissão salva
     */
    Permissao salvar(Permissao permissao);

    /**
     * remove uma permissão do banco de dados
     * @param permissao
     */
     void remover(Permissao permissao);

}
