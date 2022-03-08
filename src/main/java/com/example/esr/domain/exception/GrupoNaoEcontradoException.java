package com.example.esr.domain.exception;

public class GrupoNaoEcontradoException extends EntidadeNaoEncontradaException {

    public GrupoNaoEcontradoException(String msg) {
        super(msg);
    }

    public GrupoNaoEcontradoException(Long id) {
        this(String.format("Não existe cadastro de grupo com o código %d",id));
    }

}
