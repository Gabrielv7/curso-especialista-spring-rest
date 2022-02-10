package com.example.esr.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public EstadoNaoEncontradoException(String msg){
        super(msg);
    }

    public EstadoNaoEncontradoException(Long id){

        this(String.format( "Não existe cadastro de estado com o código %d.", id));

    }
}
