package com.example.esr.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradaException(String msg){
        super(msg);
    }

    public CidadeNaoEncontradaException(Long id){

        this(String.format("Não existe cadastro de cidade com o código %d.", id));

    }
}
