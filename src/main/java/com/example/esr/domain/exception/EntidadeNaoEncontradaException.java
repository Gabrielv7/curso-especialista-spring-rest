package com.example.esr.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {

    protected EntidadeNaoEncontradaException(String msg){
        super(msg);
    }

}
