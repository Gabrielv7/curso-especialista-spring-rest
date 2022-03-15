package com.example.esr.domain.exception;

public class PermissaoNaoEcontradaException extends EntidadeNaoEncontradaException{

    public PermissaoNaoEcontradaException(String msg) {
        super(msg);
    }

    public PermissaoNaoEcontradaException(Long id) {
        this(String.format("Não existe permissão de código %d", id));
    }

}
