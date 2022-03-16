package com.example.esr.domain.service;

import com.example.esr.domain.model.Usuario;
import com.example.esr.domain.exception.NegocioException;

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

    /**
     * Atualiza a senha de um usuário
     * @param id Identificador do usuário
     * @param senhaAtual senha atual do usuário
     * @param novaSenha nova senha do usuário
     */
    void atualizarSenha(Long id, String senhaAtual, String novaSenha);

    /**
     * Faz a associação de um usúario a um grupo
     * @param usuarioId Identificador do usuário
     * @param grupoId Identificador do grupo
     * @throws NegocioException
     */
    void associaGrupo(Long usuarioId, Long grupoId);

    /**
     * Faz a desassociação de um usúario a um grupo
     * @param usuarioId Identificador do usuário
     * @param grupoId Identificador do grupo
     * @throws NegocioException
     */
    void desassociaGrupo(Long usuarioId, Long grupoId);

}
