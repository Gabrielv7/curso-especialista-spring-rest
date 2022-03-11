package com.example.esr.domain.service;

import com.example.esr.domain.model.Usuario;

import java.util.List;

public interface UsuarioService {

    /**
     * Busca uma lista de usuários
     * @return
     */
    List<Usuario> listar();

    /**
     * Busca um único usuário
     * @param id Identificador do usuário
     * @return
     */
    Usuario buscarOuFalhar(Long id);

    /**
     *Adiciona um usúario no banco de dados
     * @param usuario
     * @return usuário salvo
     */
    Usuario salvar(Usuario usuario);

    void atualizarSenha(Long id, String senhaAtual, String novaSenha);

}
