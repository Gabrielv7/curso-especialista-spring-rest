package com.example.esr.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    public RestauranteNaoEncontradoException(String msg){
        super(msg);
    }

    public RestauranteNaoEncontradoException(Long id){

        this(String.format("Não existe cadastro de restaurante com o código %d.", id));

    }
}
