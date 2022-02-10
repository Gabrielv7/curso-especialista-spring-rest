package com.example.esr.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CozinhaNaoEncontradaException(String msg){
        super(msg);
    }

    public CozinhaNaoEncontradaException(Long id){

        this(String.format("Não existe cadastro de cozinha com o código %d.", id));

    }
}
