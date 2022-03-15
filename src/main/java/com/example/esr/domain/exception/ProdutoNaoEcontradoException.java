package com.example.esr.domain.exception;

public class ProdutoNaoEcontradoException extends EntidadeNaoEncontradaException{

    public ProdutoNaoEcontradoException(String msg) {
        super(msg);
    }

    public ProdutoNaoEcontradoException(Long produtoId, Long restauranteId) {
        this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d.",
                                                                                    produtoId, restauranteId));
    }

}
