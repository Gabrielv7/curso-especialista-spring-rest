package com.example.esr.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

    public UsuarioNaoEncontradoException(String msg) {
        super(msg);
    }

    public UsuarioNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de usuário com o código %d.", id));
    }

}
