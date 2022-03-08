package com.example.esr.domain.exception;

public class FormaDePagamentoNaoEcontradaException extends EntidadeNaoEncontradaException{

    public FormaDePagamentoNaoEcontradaException(String msg) {
        super(msg);
    }

    public FormaDePagamentoNaoEcontradaException(Long id) {
        this(String.format("Não existe cadastro de forma de pagamento com o código %d", id));
    }
}
