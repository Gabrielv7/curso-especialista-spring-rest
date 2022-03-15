package com.example.esr.domain.service;

import com.example.esr.domain.model.Permissao;
import com.example.esr.domain.exception.PermissaoNaoEcontradaException;

public interface PermissaoService {

    /**
     * Busca uma única permissão pelo Id
     * @param id Identificador da permissão
     * @throws PermissaoNaoEcontradaException
     * @return permissão
     */
    Permissao buscarOuFalhar(Long id);

}
